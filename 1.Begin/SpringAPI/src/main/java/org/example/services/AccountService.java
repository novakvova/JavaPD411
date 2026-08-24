package org.example.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.config.RolesConstants;
import org.example.dtos.account.RegisterDto;
import org.example.entities.RoleEntity;
import org.example.entities.UserEntity;
import org.example.repositories.IRoleRepository;
import org.example.repositories.IUserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //Для DI - Dependency Injection щоб усе працювало як сало
public class AccountService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity register(RegisterDto dto, HttpServletRequest request) {
        UserEntity user = new UserEntity();
        if(userRepository.findByUsername(dto.getUsername()).isPresent())
            throw new IllegalArgumentException("Username already exists");
        if(userRepository.findByEmail(dto.getEmail()).isPresent())
            throw new IllegalArgumentException("Email already exists");
        if(!dto.getPassword().equals(dto.getConfirmPassword()))
            throw new IllegalArgumentException("Passwords do not match");
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            try {
                new SaveUserImageService().saveUserImage(dto.getImage(), dto.getUsername());
                user.setImage(dto.getUsername() + ".jpg");
            } catch (Exception e) {
                throw new RuntimeException("Failed to save user image", e);
            }
        }

        RoleEntity roleUser = roleRepository.findByName(RolesConstants.UserRole).orElseThrow(()->new RuntimeException("User role not found"));
        user.getRoles().add(roleUser);

        UserEntity saveUser = userRepository.save(user);

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(saveUser, null, saveUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        request.getSession(true).setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext()
        );
        return saveUser;
    }
}

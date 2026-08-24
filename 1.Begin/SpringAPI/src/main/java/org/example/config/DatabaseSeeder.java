package org.example.config;


import lombok.AllArgsConstructor;
import org.example.entities.RoleEntity;
import org.example.entities.UserEntity;
import org.example.repositories.IRoleRepository;
import org.example.repositories.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

//    public DatabaseSeeder(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public void run(String... args) {
        String testEmail = "novakvova@gmail.com";

        seedRoles();

        if (userRepository.findByEmail(testEmail).isEmpty()) {
            UserEntity user = new UserEntity();
            user.setEmail(testEmail);
            user.setUsername(testEmail); // Обов'язково робимо username = email
            user.setPassword(passwordEncoder.encode("123456"));
            RoleEntity role = roleRepository.findByName(RolesConstants.AdminRole).orElseThrow();
            user.setRoles(Set.of(role));
            userRepository.save(user);
            System.out.println(">>> SEED: Користувача засіяно: "+testEmail+" / 123456");
        }
    }

    private void seedRoles() {
        List<String> roles = RolesConstants.Roles;

        for (String roleName : roles) {
            boolean exists = roleRepository.findByName(roleName).isPresent();
            if (!exists) {
                RoleEntity role = new RoleEntity();
                role.setName(roleName);
                roleRepository.save(role);
                System.out.println("Додано роль: " + roleName);
            } else {
                System.out.println("Роль уже існує: " + roleName);
            }
        }
    }
}

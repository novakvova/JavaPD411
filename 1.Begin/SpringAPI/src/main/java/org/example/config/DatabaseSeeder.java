package org.example.config;


import org.example.entities.UserEntity;
import org.example.repositories.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String testEmail = "novakvova@gmail.com";

        if (userRepository.findByEmail(testEmail).isEmpty()) {
            UserEntity user = new UserEntity();
            user.setEmail(testEmail);
            user.setUsername(testEmail); // Обов'язково робимо username = email
            user.setPassword(passwordEncoder.encode("123456"));

            userRepository.save(user);
            System.out.println(">>> SEED: Користувача засіяно: "+testEmail+" / 123456");
        }
    }
}

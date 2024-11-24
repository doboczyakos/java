package com.example.gyakorlat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
class SeedDatabase {
    @Bean
    CommandLineRunner initDatabase(UserRepo userRepository) {
        var passwordEncoder = new BCryptPasswordEncoder();
        return args -> {
            if (userRepository.count() == 0) {
                Iterable<User> users = List.of(new User[] {
                        new User("admin", passwordEncoder.encode("Asdf1234"), "Admin", "ADMIN"),
                        new User("user", passwordEncoder.encode("Uiop1234"), "Felhasználó", "USER"),
                        new User("visitor", passwordEncoder.encode("Vbnm1234"), "Látogató", "VISITOR")
                });
                userRepository.saveAll(users);
            }
        };
    }
}
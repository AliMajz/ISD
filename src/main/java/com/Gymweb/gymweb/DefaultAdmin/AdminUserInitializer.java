package com.Gymweb.gymweb.DefaultAdmin;

import com.Gymweb.gymweb.entity.Role;
import com.Gymweb.gymweb.entity.User;
import com.Gymweb.gymweb.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String email = "adminroot@gmail.com";  // hardcoded email
        String password = "admin123";           // hardcoded password

        if (userRepo.findByEmail(email).isEmpty()) {
            User admin = User.builder()
                    .fname("Root")
                    .lname("Admin")
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(Role.ADMIN)
                    .build();

            userRepo.save(admin);
            System.out.println("✅ Root admin user created.");
        } else {
            System.out.println("ℹ️ Admin user already exists.");
        }
    }
}

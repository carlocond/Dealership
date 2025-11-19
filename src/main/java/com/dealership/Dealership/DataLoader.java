package com.dealership.Dealership;

import com.dealership.Dealership.entity.Role;
import com.dealership.Dealership.entity.User;
import com.dealership.Dealership.repository.CarRepo;
import com.dealership.Dealership.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner { //Inizializzo un DataLoader in modo che all'avvio dell'app venga creato un Admin di default

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final UserRepo userRepo;
    private final CarRepo carRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findByEmail("admin@local.com").isEmpty()){
            User admin = User.builder()
                    .fname("Admin")
                    .lname("Admin")
                    .email("admin@local.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build();
            userRepo.save(admin);
            log.info("Admin creato con successo: " + admin.getEmail());
        }
        else{
            log.info("Admin già presente.");
        }
    }
}

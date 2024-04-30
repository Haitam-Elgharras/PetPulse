package ma.petpulse.petpulsecore;

import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.dao.repositories.UserRepository;
import ma.petpulse.petpulsecore.enumerations.Role;
import org.springframework.boot.CommandLineRunner;
import ma.petpulse.petpulsecore.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class PetpulseCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetpulseCoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(UserRepository ur, PasswordEncoder pe) {
        return (args) -> {

            User petOwner = new User();
            petOwner.setFirstName("petOwnerUsername");
            petOwner.setLastName("petOwnerUsername");
            petOwner.setPassword(pe.encode("petOwnerPassword"));
            petOwner.setEmail("petOwnerEmail@example.com");
            petOwner.setRole(Role.ROLE_PET_OWNER);

            User admin = new User();
            admin.setFirstName("adminUsername");
            admin.setLastName("adminUsername");
            admin.setPassword(pe.encode("petOwnerPassword"));
            admin.setEmail("adminEmail@example.com");
            admin.setRole(Role.ROLE_ADMIN);

            ur.save(petOwner);
            ur.save(admin);


        };
    }
}

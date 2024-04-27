package ma.petpulse.petpulsecore;

import ma.petpulse.petpulsecore.config.JwtConfig;
import ma.petpulse.petpulsecore.service.services.implementations.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class PetpulseCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetpulseCoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(UserServiceImpl userService) {
        return (args) -> {
            // log the user
            System.out.println(userService.getAllUsers());


        };
    }
}

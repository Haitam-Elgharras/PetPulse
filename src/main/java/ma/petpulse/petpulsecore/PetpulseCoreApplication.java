package ma.petpulse.petpulsecore;

import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.enumerations.Role;
import ma.petpulse.petpulsecore.enumerations.Specie;
import ma.petpulse.petpulsecore.service.services.implementations.PetServiceImpl;
import ma.petpulse.petpulsecore.service.services.implementations.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PetpulseCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetpulseCoreApplication.class, args);
    }

}

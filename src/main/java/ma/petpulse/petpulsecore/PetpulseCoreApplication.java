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

    @Bean
    CommandLineRunner start(UserServiceImpl userService, PetServiceImpl petService) {
        return args -> {
            // Create a user
            User ilyas = new User();
            ilyas.setFirstName("Ilyas");
            ilyas.setLastName("El Mabrouki");
            ilyas.setEmail("ilyas@gmail.com");
            userService.addUser(ilyas);

            // Create a pet
            Pet pet1 = new Pet();
            pet1.setName("Pet1");
            pet1.setSpecie(Specie.DOG);
            pet1.setBreed("Breed1");
            pet1.setAge(3);
            pet1.setImageURL("https://example.com/pet1.jpg");
            pet1.setOwner(ilyas); // Set ilyas as the owner
            petService.addPet(pet1);
        };
    }

}

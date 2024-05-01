package ma.petpulse.petpulsecore;

import ma.petpulse.petpulsecore.dao.entities.AdoptionApplication;
import ma.petpulse.petpulsecore.dao.entities.Listing;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.dao.repositories.AdoptionApplicationRepository;
import ma.petpulse.petpulsecore.dao.repositories.ListingRepository;
import ma.petpulse.petpulsecore.dao.repositories.PetRepository;
import ma.petpulse.petpulsecore.dao.repositories.UserRepository;
import ma.petpulse.petpulsecore.enumerations.AdoptionStatus;
import ma.petpulse.petpulsecore.enumerations.Role;
import ma.petpulse.petpulsecore.enumerations.Specie;
import org.springframework.boot.CommandLineRunner;
import ma.petpulse.petpulsecore.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class PetpulseCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetpulseCoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(UserRepository userRepository, PetRepository petRepository, ListingRepository listingRepository, AdoptionApplicationRepository adoptionApplicationRepository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            // Create some users
            User user1 = new User(null, "John", "Doe", passwordEncoder.encode("password"), "john.doe@example.com", "New York", Role.ROLE_PET_OWNER, true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user2 = new User(null, "Jane", "Doe", passwordEncoder.encode("password"), "jane.doe@example.com", "Los Angeles", Role.ROLE_PET_OWNER, true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            userRepository.saveAll(List.of(user1, user2));

            // Create some pets for the users
            Pet pet1 = new Pet(null, "Rex", Specie.DOG, "Labrador", 5, "imageURL", new ArrayList<>(), new ArrayList<>(), user1);
            Pet pet2 = new Pet(null, "Mittens", Specie.CAT, "Persian", 3, "imageURL", new ArrayList<>(), new ArrayList<>(), user2);
            petRepository.saveAll(List.of(pet1, pet2));

            // Create some listings for the pets
            Listing listing1 = new Listing(null, "Friendly dog", 100L, AdoptionStatus.AVAILABLE, user1, pet1, new ArrayList<>());
            Listing listing2 = new Listing(null, "Cute cat", 200L, AdoptionStatus.AVAILABLE, user2, pet2, new ArrayList<>());
            listingRepository.saveAll(List.of(listing1, listing2));

            // private String reason;
            //
            //    private int numberOfPets;

            // Create some adoption applications for the listings
            AdoptionApplication application1 = new AdoptionApplication(null, "I love dogs", 1, user2, listing2);
            AdoptionApplication application2 = new AdoptionApplication(null, "I love cats", 1, user1, listing1);

        };
    }
}

package ma.petpulse.petpulsecore;

import ma.petpulse.petpulsecore.dao.entities.*;
import ma.petpulse.petpulsecore.dao.repositories.*;
import ma.petpulse.petpulsecore.enumerations.Role;
import ma.petpulse.petpulsecore.enumerations.Specie;
import ma.petpulse.petpulsecore.enumerations.Type;
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
    public CommandLineRunner start(UserRepository userRepository, PetRepository petRepository,
                                   ReportRepository reportRepository, AdoptionApplicationRepository adoptionApplicationRepository,
                                   PasswordEncoder passwordEncoder,
                                   LostApplicationRepository lostApplicationRepository
    ) {
        return (args) -> {
          
        };


        /*@Bean
        public CommandLineRunner start(UserRepository userRepository,
                                       PetRepository petRepository,
                                       PasswordEncoder passwordEncoder,
                                       PetImageRepository petImageRepository) {
            return (args) -> {

                User user1 = new User();
                user1.setFirstName("user1");
                user1.setLastName("user1");
                user1.setPassword(passwordEncoder.encode("password1"));
                user1.setEmail("user1@example.com");
                user1.setRole(Role.ROLE_PET_OWNER);
                userRepository.save(user1);

                Pet pet = new Pet();
                pet.setName("Dog");
                pet.setAge(5);
                pet.setSpecie(Specie.DOG);
                pet.setOwner(user1);
                pet.setBreed("Bulldog"); // Set breed
                petRepository.save(pet);

                PetImage petImage1 = new PetImage();
                petImage1.setUrl("dog_image_url1");
                petImage1.setPet(pet);
                petImageRepository.save(petImage1);

                PetImage petImage2 = new PetImage();
                petImage2.setUrl("dog_image_url2");
                petImage2.setPet(pet);
                petImageRepository.save(petImage2);
            };*/
        //adoptionApplicationRepository.save(application2);*/
//        };
    }
}

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
            // Create some User entities
            User user1 = new User();
            user1.setFirstName("user1");
            user1.setLastName("user1");
            user1.setPassword(passwordEncoder.encode("password1"));
            user1.setEmail("user1@example.com");
            user1.setRole(Role.ROLE_PET_OWNER);

            User user2 = new User();
            user2.setFirstName("user2");
            user2.setLastName("user2");
            user2.setPassword(passwordEncoder.encode("password2"));
            user2.setEmail("user2@example.com");
            user2.setRole(Role.ROLE_PET_OWNER);

            userRepository.save(user1);
            userRepository.save(user2);

            // Create some Pet entities
            Pet pet1 = new Pet();
            pet1.setName("Dog");
            pet1.setAge(5);
            pet1.setOwner(user1);
//            pet1.setImageURL("dog_image_url"); // Set imageURL
            pet1.setBreed("Bulldog"); // Set breed
            pet1.setSpecie(Specie.DOG);

            Pet pet2 = new Pet();
            pet2.setName("Cat");
            pet2.setAge(3);
            pet2.setOwner(user2);
//            pet2.setImageURL("cat_image_url"); // Set imageURL
            pet2.setBreed("Persian"); // Set breed
            pet2.setSpecie(Specie.CAT);

            Pet pet3 = new Pet();
            pet3.setName("bobi");
            pet3.setAge(5);
            pet3.setOwner(user1);
//            pet1.setImageURL("dog_image_url"); // Set imageURL
            pet3.setBreed("Bulldog"); // Set breed
            pet3.setSpecie(Specie.DOG);

            Pet pet4 = new Pet();
            pet4.setName("kitty");
            pet4.setAge(3);
            pet4.setOwner(user2);
//            pet2.setImageURL("cat_image_url"); // Set imageURL
            pet4.setBreed("Persian"); // Set breed
            pet4.setSpecie(Specie.CAT);




            petRepository.save(pet1);
            petRepository.save(pet2);
            petRepository.save(pet3);
            petRepository.save(pet4);


            // Create some Report entities
            Report report1 = new Report();
            report1.setTitle("Persian cat for adoption");
            report1.setDescription("very cute cat");
            report1.setType(Type.ADOPTION);
            report1.setUser(user2);
            report1.setPet(pet2);

            Report report2 = new Report();
            report2.setTitle("bidpuppy for adoption");
            report2.setDescription("very cute puppy");
            report2.setType(Type.ADOPTION);
            report2.setUser(user1);
            report2.setPet(pet1);

            Report report3 = new Report();
            report3.setTitle("Persian cat for adoption");
            report3.setDescription("very cute cat");
            report3.setType(Type.ADOPTION);
            report3.setUser(user2);
            report3.setPet(pet4);

            Report report4 = new Report();
            report4.setTitle("bidpuppy for adoption");
            report4.setDescription("very cute puppy");
            report4.setType(Type.ADOPTION);
            report4.setUser(user2);
            report4.setPet(pet3);

            Report report5 = new Report();
            report5.setTitle("bidpuppy for adoption");
            report5.setDescription("very cute puppy");
            report5.setType(Type.ADOPTION);
            report5.setUser(user2);
            report5.setPet(pet3);






            reportRepository.save(report1);
            reportRepository.save(report2);
            reportRepository.save(report3);
            reportRepository.save(report4);
            reportRepository.save(report5);


            // Create some AdoptionApplication entities
            AdoptionApplication application1 = new AdoptionApplication();
            application1.setReason("I have a big yard");
            application1.setNumberOfPets(0);
            application1.setUser(user1);
            application1.setReport(report1);
            application1.setPetExperience("I have a dog");

            AdoptionApplication application2 = new AdoptionApplication();
            application2.setReason("I love cats");
            application2.setNumberOfPets(1);
            application2.setUser(user2);
            application2.setReport(report2);
            application2.setPetExperience("I have a cat");
            adoptionApplicationRepository.save(application1);
            adoptionApplicationRepository.save(application2);


            // create some lost application entities
            LostApplication lostApplication1 = new LostApplication();
            lostApplication1.setContactInfo("contact info 1");
            lostApplication1.setProofImage("proof image 1");
            lostApplication1.setReport(report1);
            lostApplication1.setUser(user1);
            lostApplication1.setSightingLocation("Rabat");
            lostApplication1.setMessage("message 1");


            lostApplicationRepository.save(lostApplication1);
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

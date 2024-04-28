package ma.petpulse.petpulsecore;

import ma.petpulse.petpulsecore.config.JwtConfig;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.enumerations.Status;
import ma.petpulse.petpulsecore.service.dtos.ReportDto;
import ma.petpulse.petpulsecore.service.services.implementations.ReportServiceImpl;
import ma.petpulse.petpulsecore.service.services.implementations.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class PetpulseCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetpulseCoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(UserServiceImpl userService, ReportServiceImpl reportService) {
        return (args) -> {
            ReportDto reportDto=new ReportDto();
            reportDto.setCity("Meknes");
            reportDto.setAddress("53 groupe 2 bmo ");
            reportDto.setDescription("This is a dog ");
            reportDto.setLatitude(3002.3);
            reportDto.setLongitude(79203.2);
            reportDto.setStatus(Status.PENDING);
            reportDto.setType("test type");
            reportDto.setTitle("Found report ...");

            Pet pet=new Pet();
            pet
            reportDto.setPet(new Pet());
            reportDto.setPetOwnerId(2L);
            reportService.saveReport(reportDto);
        };
    }
}

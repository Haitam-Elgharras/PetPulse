package ma.petpulse.petpulsecore;

import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.Report;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.enumerations.Role;
import ma.petpulse.petpulsecore.enumerations.Specie;
import ma.petpulse.petpulsecore.service.mappers.ReportMapper;
import ma.petpulse.petpulsecore.service.services.implementations.PetServiceImpl;
import ma.petpulse.petpulsecore.service.services.implementations.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import ma.petpulse.petpulsecore.config.JwtConfig;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.enumerations.Status;
import ma.petpulse.petpulsecore.service.dtos.ReportDto;
import ma.petpulse.petpulsecore.service.services.implementations.ReportServiceImpl;
import ma.petpulse.petpulsecore.service.services.implementations.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class PetpulseCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetpulseCoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(UserServiceImpl userService, ReportServiceImpl reportService, PetServiceImpl petService, ReportMapper reportMapper) {
        return (args) -> {
          /*  User u1 = new User();
            u1.setActive(true);
            u1.setCity("Meknes");
            u1.setEmail("y,zendour2@©mail.com");
            u1.setPassword("Brad2016");
            u1.setFirstName("bazi7");
            u1.setLastName("bouchta");
            u1.setRole(Role.ROLE_PET_OWNER);
            userService.addUser(u1);
            User u2 = new User();
            u2.setActive(true);
            u2.setCity("fes");
            u2.setEmail("smchhh@gmail.com");
            u2.setPassword("Brad2016@");
            u2.setFirstName("bazi7");
            u2.setLastName("bouchta");
            u2.setRole(Role.ROLE_PET_OWNER);
            userService.addUser(u2);
            /////////////////

            Pet p1 = new Pet(null, "dog", Specie.BIRD, "fffff", 20, "ddfjjfjf", null, u1);
            Pet p2 = new Pet(null, "cat", Specie.BIRD, "fffff", 20, "ddfjjfjf", null, u2);
            petService.addPet(p1);
            petService.addPet(p2);

            //////////
            Report report = new Report(2L, "this is updated report", "this is test desc", 30029, 49993, "meknes", "fjfjf fjfj", "ffff", Status.PENDING, "fdddd", null, null, false, null, p1, u1);
            Report report1 = new Report(null, "this is test report", "this is test desc", 30029, 49993, "meknes", "fjfjf fjfj", "ffff", Status.PENDING, "fdddd", null, null, false, null, p1, u2);
            Report report2 = new Report(null, "this is test report", "this is test desc", 30029, 49993, "meknes", "fjfjf fjfj", "ffff", Status.PENDING, "fdddd", null, null, false, null, p1, u2);
            reportService.saveReport(report);
            reportService.saveReport(report1);
            reportService.saveReport(report2);
            reportService.updateReport(report);
            //done  reportService.deleteReport(1L);
            //done  List<Report> reports= reportService.getAllReports();
           //done Report reporteeeee = reportService.getReportById(2L);
            List<Report> reportsss=reportService.getReportsByUserId(16L);*/
        };
    }
}

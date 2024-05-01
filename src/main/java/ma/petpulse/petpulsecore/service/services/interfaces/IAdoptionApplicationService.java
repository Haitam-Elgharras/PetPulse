package ma.petpulse.petpulsecore.service.services.interfaces;

import ma.petpulse.petpulsecore.dao.entities.AdoptionApplication;
import ma.petpulse.petpulsecore.dao.entities.User;

import java.util.List;

public interface IAdoptionApplicationService {
    AdoptionApplication createApplication(AdoptionApplication application);
    AdoptionApplication getApplicationById(Long id);
    AdoptionApplication updateApplication(AdoptionApplication application);
    void deleteApplication(Long id);
    List<AdoptionApplication> getAllApplications();
    List<AdoptionApplication> getApplicationsByApplicant(User applicant);
}
package ma.petpulse.petpulsecore.service.services.implementations;

import lombok.RequiredArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.AdoptionApplication;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.dao.repositories.AdoptionApplicationRepository;
import ma.petpulse.petpulsecore.service.services.interfaces.IAdoptionApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionApplicationService implements IAdoptionApplicationService {
    private final AdoptionApplicationRepository adoptionApplicationRepository;

    @Override
    public AdoptionApplication createApplication(AdoptionApplication application) {
        return adoptionApplicationRepository.save(application);
    }

    @Override
    public AdoptionApplication getApplicationById(Long id) {
        return adoptionApplicationRepository.findById(id).orElse(null);
    }

    @Override
    public AdoptionApplication updateApplication(AdoptionApplication application) {
        return adoptionApplicationRepository.save(application);
    }

    @Override
    public void deleteApplication(Long id) {
        adoptionApplicationRepository.deleteById(id);
    }

    @Override
    public List<AdoptionApplication> getAllApplications() {
        return adoptionApplicationRepository.findAll();
    }

    @Override
    public List<AdoptionApplication> getApplicationsByApplicant(User applicant) {
        return adoptionApplicationRepository.findByUserId(applicant.getId());
    }
}
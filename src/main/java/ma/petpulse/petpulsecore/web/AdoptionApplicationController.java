package ma.petpulse.petpulsecore.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.AdoptionApplication;
import ma.petpulse.petpulsecore.dao.entities.Listing;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.exceptions.ApplicationNotFoundException;
import ma.petpulse.petpulsecore.exceptions.ListingNotFoundException;
import ma.petpulse.petpulsecore.service.services.interfaces.IAdoptionApplicationService;
import ma.petpulse.petpulsecore.service.services.interfaces.IJwtService;
import ma.petpulse.petpulsecore.service.services.interfaces.IListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class AdoptionApplicationController {
    private final IAdoptionApplicationService adoptionApplicationService;
    private final IListingService listingService;
    private final IJwtService jwtService;

    @GetMapping
    public ResponseEntity<List<AdoptionApplication>> getAllApplications() {
        return ResponseEntity.ok(adoptionApplicationService.getAllApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionApplication> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(adoptionApplicationService.getApplicationById(id));
    }

    @PostMapping
    public ResponseEntity<AdoptionApplication> createApplication(@RequestBody @Valid AdoptionApplication application, @RequestParam Long listingId) {
        User authenticatedUser = jwtService.getAuthenticatedUser();
        application.setUser(authenticatedUser);

        Listing listing = listingService.getListingById(listingId);
        if (listing == null)
            throw new ListingNotFoundException("Listing with id " + listingId + " not found");

        application.setListing(listing);

        return ResponseEntity.ok(adoptionApplicationService.createApplication(application));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionApplication> updateApplication(@PathVariable Long id, @RequestBody AdoptionApplication application) {
        AdoptionApplication existingApplication = adoptionApplicationService.getApplicationById(id);
        if (existingApplication == null)
            throw new ApplicationNotFoundException("Application with id " + id + " not found");

        if (!jwtService.getAuthenticatedUser().getId().equals(existingApplication.getUser().getId()))
            throw new AccessDeniedException("Authenticated user does not have access to update this application");

        application.setId(id);

        return ResponseEntity.ok(adoptionApplicationService.updateApplication(application));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        AdoptionApplication application = adoptionApplicationService.getApplicationById(id);

        if (application == null)
            throw new ApplicationNotFoundException("Application with id " + id + " not found");

        if (!jwtService.getAuthenticatedUser().getId().equals(application.getUser().getId())) {
            throw new AccessDeniedException("Authenticated user does not have access to delete this application");
        }
        adoptionApplicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}
package ma.petpulse.petpulsecore.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.Listing;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.exceptions.ListingNotFoundException;
import ma.petpulse.petpulsecore.exceptions.PetNotFoundException;
import ma.petpulse.petpulsecore.service.services.interfaces.IJwtService;
import ma.petpulse.petpulsecore.service.services.interfaces.IListingService;
import ma.petpulse.petpulsecore.service.services.interfaces.IPetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {
    private final IListingService listingService;
    private final IPetService petService;
    private final IJwtService jwtService;

    @GetMapping
    public ResponseEntity<List<Listing>> getAllListings() {
        return ResponseEntity.ok(listingService.getAllListings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable Long id) {
        return ResponseEntity.ok(listingService.getListingById(id));
    }

    @PostMapping
    public ResponseEntity<Listing> createListing(@RequestBody Listing listing, @RequestParam Long petId) {
        User authenticatedUser = jwtService.getAuthenticatedUser();
        listing.setOwner(authenticatedUser);

        Pet pet = petService.getPetById(petId);
        if (pet == null)
            throw new PetNotFoundException("Pet with id " + petId + " not found");

        listing.setPet(pet);

        return ResponseEntity.ok(listingService.createListing(listing));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> updateListing(@PathVariable Long id, @RequestBody @Valid Listing listing) {
        Listing existingListing = listingService.getListingById(id);
        if(existingListing == null)
            throw new ListingNotFoundException("Listing with id " + id + " not found");

        if (!jwtService.getAuthenticatedUser().getId().equals(existingListing.getOwner().getId()))
            throw new AccessDeniedException("Authenticated user does not have access to update this listing");

        listing.setId(id);

        return ResponseEntity.ok(listingService.updateListing(listing));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        Listing listing = listingService.getListingById(id);
        if (!jwtService.getAuthenticatedUser().getId().equals(listing.getOwner().getId())) {
            throw new AccessDeniedException("Authenticated user does not have access to delete this listing");
        }
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }
}
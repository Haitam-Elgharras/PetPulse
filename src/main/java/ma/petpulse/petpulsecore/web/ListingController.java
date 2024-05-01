package ma.petpulse.petpulsecore.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.Listing;
import ma.petpulse.petpulsecore.service.services.interfaces.IJwtService;
import ma.petpulse.petpulsecore.service.services.interfaces.IListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {
    private final IListingService listingService;
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
    public ResponseEntity<Listing> createListing(@RequestBody Listing listing) {
        // TODO: we must get the authenticated user from the jwt token and set it as the owner of the listing
        //  and also get the pet id as a param or something cause we can't set the owner and the pet in the request body
        return ResponseEntity.ok(listingService.createListing(listing));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> updateListing(@PathVariable Long id, @RequestBody Listing listing) {
        // TODO: check if the listing exists to avoid null pointer exception
        if (!jwtService.getAuthenticatedUser().getId().equals(listing.getOwner().getId())) {
            throw new AccessDeniedException("Authenticated user does not have access to update this listing");
        }
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
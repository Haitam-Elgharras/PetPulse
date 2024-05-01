package ma.petpulse.petpulsecore.service.services.interfaces;

import ma.petpulse.petpulsecore.dao.entities.Listing;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.enumerations.AdoptionStatus;

import java.util.List;

public interface IListingService {
    Listing createListing(Listing listing);
    Listing getListingById(Long id);
    Listing updateListing(Listing listing);
    void deleteListing(Long id);
    List<Listing> getAllListings();
    List<Listing> getListingsByStatus(AdoptionStatus status);
    List<Listing> getListingsByOwner(User owner);
    List<Listing> getListingsByPet(Pet pet);
}

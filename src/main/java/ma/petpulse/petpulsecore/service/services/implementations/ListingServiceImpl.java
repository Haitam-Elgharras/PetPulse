package ma.petpulse.petpulsecore.service.services.implementations;

import lombok.RequiredArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.Listing;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.dao.repositories.ListingRepository;
import ma.petpulse.petpulsecore.enumerations.AdoptionStatus;
import ma.petpulse.petpulsecore.service.services.interfaces.IListingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements IListingService {
    private final ListingRepository listingRepository;

    @Override
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    @Override
    public Listing getListingById(Long id) {
        return listingRepository.findById(id).orElse(null);
    }

    @Override
    public Listing updateListing(Listing listing) {
        return listingRepository.save(listing);
    }

    @Override
    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }

    @Override
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    @Override
    public List<Listing> getListingsByStatus(AdoptionStatus status) {
        return listingRepository.findByStatus(status);
    }

    @Override
    public List<Listing> getListingsByOwner(User owner) {
        return listingRepository.findByOwnerId(owner.getId());
    }

    @Override
    public List<Listing> getListingsByPet(Pet pet) {
        return listingRepository.findByPetId(pet.getId());
    }
}
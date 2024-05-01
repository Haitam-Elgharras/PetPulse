package ma.petpulse.petpulsecore.dao.repositories;

import ma.petpulse.petpulsecore.dao.entities.Listing;
import ma.petpulse.petpulsecore.enumerations.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByStatus(AdoptionStatus status);
    List<Listing> findByOwnerId(Long ownerId);
    List<Listing> findByPetId(Long petId);
}
package ma.petpulse.petpulsecore.service.services.interfaces;

import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;

import java.util.List;

public interface IPetService {
    void addPet(Pet pet);
    void updatePet(Pet pet);
    void deletePet(Pet pet);
    List<Pet> getAllPets();
    Pet getPetById(Long id);
    User getOwner(Pet pet);
}

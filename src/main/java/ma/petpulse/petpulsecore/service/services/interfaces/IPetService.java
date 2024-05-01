package ma.petpulse.petpulsecore.service.services.interfaces;

import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.service.dtos.PetDto;
import ma.petpulse.petpulsecore.service.dtos.UserDto;

import java.util.List;

public interface IPetService {
    PetDto savePet(PetDto pet, Long ownerId);
    PetDto updatePet(PetDto pet);
    void deletePet(Long id);
    List<PetDto> getAllPets();
    PetDto getPetById(Long id);
}

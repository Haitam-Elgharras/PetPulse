package ma.petpulse.petpulsecore.service.services.implementations;

import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.dao.repositories.PetRepository;
import ma.petpulse.petpulsecore.service.services.interfaces.IPetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements IPetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {this.petRepository = petRepository;}

    @Override
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void deletePet(Pet pet) {
        petRepository.delete(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public User getOwner(Pet pet) {
        return pet.getOwner();
    }
}

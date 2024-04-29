package ma.petpulse.petpulsecore.web;

import lombok.RequiredArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.enumerations.Specie;
import ma.petpulse.petpulsecore.service.dtos.PetDto;
import ma.petpulse.petpulsecore.service.dtos.UserDto;
import ma.petpulse.petpulsecore.service.mappers.UserMapper;
import ma.petpulse.petpulsecore.service.services.implementations.PetServiceImpl;
import ma.petpulse.petpulsecore.service.services.implementations.UserServiceImpl;
import ma.petpulse.petpulsecore.service.services.interfaces.IPetService;
import ma.petpulse.petpulsecore.service.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class PetController {
    private final IPetService petService;
    private final IUserService userService;
    private final UserMapper userMapper;


    @GetMapping("/pets")
    public ArrayList<Pet> getPets() {
        return (ArrayList<Pet>) petService.getAllPets();
    }

    @GetMapping("/pets/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @PostMapping("/pets/save")
    public ResponseEntity<Pet> addPet(@Valid @RequestBody PetDto petDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return a bad request response
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User owner = userService.getUserById(petDto.getOwnerId());
        if (owner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Create a new pet and set its attributes
        Pet pet = new Pet();
        pet.setName(petDto.getName());
        pet.setSpecie(Specie.valueOf(petDto.getSpecie()));
        pet.setBreed(petDto.getBreed());
        pet.setAge(petDto.getAge());
        pet.setImageURL(petDto.getImageURL());

        // Set the owner of the pet
        pet.setOwner(owner);

        // Save the pet entity
        Pet savedPet = petService.addPet(pet);

        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }

    @PutMapping("/pets/update/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @Valid @RequestBody PetDto petDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return a bad request response
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User owner = userService.getUserById(petDto.getOwnerId());
        if (owner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Get the pet entity by its id
        Pet pet = petService.getPetById(id);
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update the pet entity with the new attributes
        pet.setName(petDto.getName());
        pet.setSpecie(Specie.valueOf(petDto.getSpecie()));
        pet.setBreed(petDto.getBreed());
        pet.setAge(petDto.getAge());
        pet.setImageURL(petDto.getImageURL());
        pet.setOwner(owner);

        // Save the updated pet entity
        Pet updatedPet = petService.updatePet(pet);

        return new ResponseEntity<>(updatedPet, HttpStatus.OK);
    }

    @DeleteMapping("/pets/delete/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }
}

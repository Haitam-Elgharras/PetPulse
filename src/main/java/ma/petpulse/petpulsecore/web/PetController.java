package ma.petpulse.petpulsecore.web;

import lombok.RequiredArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.enumerations.Specie;
import ma.petpulse.petpulsecore.service.dtos.PetDto;
import ma.petpulse.petpulsecore.service.mappers.UserMapper;
import ma.petpulse.petpulsecore.service.services.interfaces.IPetService;
import ma.petpulse.petpulsecore.service.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetController {
    private final IPetService petService;

    @GetMapping("/pets")
    public List<PetDto> getPets() {
        return petService.getAllPets();
    }

    @GetMapping("/pets/{id}")
    public PetDto getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE},
            value = "/pets/save")
    public PetDto addPet(
            @RequestParam("name") String name,
            @RequestParam("specie") String specie,
            @RequestParam("breed") String breed,
            @RequestParam("age") int age,
            @RequestParam("ownerId") Long ownerId,
            @RequestParam("image") MultipartFile image) {
        PetDto petDto = new PetDto();
        petDto.setName(name);
        petDto.setSpecie(specie);
        petDto.setBreed(breed);
        petDto.setAge(age);
        petDto.setOwnerId(ownerId);
        // Save the pet entity
        return petService.savePet(petDto, image);
    }

    @PutMapping("/pets/update/{id}")
    public PetDto updatePet(@PathVariable Long id, @RequestBody PetDto petDto) {
        petDto.setId(id);
        return petService.updatePet(petDto);
    }

    @DeleteMapping("/pets/delete/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }
}

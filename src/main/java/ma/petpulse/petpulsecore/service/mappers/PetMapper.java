package ma.petpulse.petpulsecore.service.mappers;

import lombok.AllArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.enumerations.Specie;
import ma.petpulse.petpulsecore.service.dtos.PetDto;
import ma.petpulse.petpulsecore.service.services.implementations.UserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetMapper {

    private UserServiceImpl userService;

    public PetDto fromPet(Pet pet){
        PetDto petDTO = new PetDto();
        BeanUtils.copyProperties(pet,petDTO);
        petDTO.setOwnerId(pet.getId());
        petDTO.setSpecie(pet.getSpecie().name());
        return petDTO;
    }

    public Pet fromPetDto(PetDto petDto){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDto,pet);
        pet.setOwner(userService.getUserById(petDto.getOwnerId()));
        pet.setSpecie(Specie.valueOf(petDto.getSpecie()));
        return pet;
    }
}

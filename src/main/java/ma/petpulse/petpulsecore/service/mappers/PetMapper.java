package ma.petpulse.petpulsecore.service.mappers;

import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.service.dtos.PetDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PetMapper {

    public PetDto fromPet(Pet pet){
        PetDto petDTO = new PetDto();
        BeanUtils.copyProperties(pet,petDTO);
        return petDTO;
    }

    public Pet fromPetDto(PetDto petDto){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDto,pet);
        return pet;
    }
}

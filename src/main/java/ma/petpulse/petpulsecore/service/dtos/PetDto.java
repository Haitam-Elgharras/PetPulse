package ma.petpulse.petpulsecore.service.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
public class PetDto {
    private Long id;
    private String name;
    private String specie;
    private String breed;
    private int age;
    private String imageURL;
    private Long ownerId;
}

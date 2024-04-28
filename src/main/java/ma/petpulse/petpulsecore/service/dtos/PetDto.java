package ma.petpulse.petpulsecore.service.dtos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PetDto {
    private Long id;

    @NotEmpty
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotEmpty
    private String specie;

    @NotEmpty
    @Size(max = 100, message = "Breed cannot exceed 100 characters")
    private String breed;

    @Positive(message = "Age must be a positive number")
    private int age;

    private String imageURL;

    @NotNull(message = "Owner ID is required")
    private Long ownerId;
}

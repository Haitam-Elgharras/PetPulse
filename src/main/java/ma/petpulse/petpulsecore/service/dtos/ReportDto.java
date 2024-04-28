package ma.petpulse.petpulsecore.service.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.PetOwner;
import ma.petpulse.petpulsecore.enumerations.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ReportDto {
    private Long id;
    private String title;
    private String description;
    private double latitude;
    private double longitude;
    private String city;
    private String address;
    private String type;
    private Status status;
    private String additionalNotes;
    private Date createdAt;
    private Date updatedAt;
    private boolean verified;
    private Pet pet;
    private PetOwner petOwner;
}

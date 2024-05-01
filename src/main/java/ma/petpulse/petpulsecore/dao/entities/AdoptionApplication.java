package ma.petpulse.petpulsecore.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "adoption_applications")
@Data
public class AdoptionApplication extends Application {
    @NotBlank(message = "Reason is required")
    private String reason;

    private int numberOfPets;
}
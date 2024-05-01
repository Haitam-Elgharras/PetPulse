package ma.petpulse.petpulsecore.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "adoption_applications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Reason is required")
    private String reason;

    private int numberOfPets;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;
}
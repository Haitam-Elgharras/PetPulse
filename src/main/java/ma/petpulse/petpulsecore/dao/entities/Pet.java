package ma.petpulse.petpulsecore.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pets")
@Data @AllArgsConstructor @NoArgsConstructor
public class Pet {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Breed is required")
    @Size(max = 100, message = "Breed cannot exceed 100 characters")
    private String breed;

    @Min(value = 0, message = "Age must be greater than or equal to 0")
    private int age;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User owner;

}

package ma.petpulse.petpulsecore.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PetOwner {
    @Id
    private Long id;
    @OneToMany(mappedBy = "petOwner", cascade = CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();
}

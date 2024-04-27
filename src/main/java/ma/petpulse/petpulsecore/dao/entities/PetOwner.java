package ma.petpulse.petpulsecore.dao.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "petowners")
public class PetOwner {
    @Id
    private Long id;
    @OneToMany(mappedBy = "petOwner", cascade = CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();
}

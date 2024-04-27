package ma.petpulse.petpulsecore.dao.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    private Long id;
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();

}

package ma.petpulse.petpulsecore.dao.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;
}

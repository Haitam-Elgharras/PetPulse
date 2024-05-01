package ma.petpulse.petpulsecore.dao.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "applications")
@Data
public class Application {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
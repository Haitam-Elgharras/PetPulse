package ma.petpulse.petpulsecore.dao.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "report_applications")
public class ReportApplication {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;
}

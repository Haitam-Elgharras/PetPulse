package ma.petpulse.petpulsecore.service.dtos;

import lombok.Data;
import ma.petpulse.petpulsecore.enumerations.ReportStatus;

import java.util.Date;

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
    private ReportStatus status;
    private String additionalNotes;
    private Date createdAt;
    private Date updatedAt;
    private boolean verified;
    private Long pet_id;
    private Long  user_id;
}

package ma.petpulse.petpulsecore.dao.repositories;

import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.Report;
import ma.petpulse.petpulsecore.enumerations.Status;
import ma.petpulse.petpulsecore.enumerations.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByUserId(Long userId);

    List<Report> findByCity(String city);

    @Query("SELECT r FROM Report r WHERE r.type = :type")
    List<Report> findReportByType(@Param("type") Type type);

    @Query("SELECT r FROM Report r " +
            "WHERE (:type IS NULL OR r.type = :type) " +
            "AND (:city IS NULL OR r.city = :city) " +
            "AND (:status IS NULL OR r.status = :status) " +
            "AND (:startDate IS NULL OR r.createdAt >= :startDate) " +
            "AND (:endDate IS NULL OR r.createdAt <= :endDate) " +
            "AND (:verified IS NULL OR r.verified = :verified) " +
            "AND (:petId IS NULL OR r.pet.id = :petId) " +
            "AND (:userId IS NULL OR r.user.id = :userId)")
    Page<Report> findReportsByFilters(Type type, String city, Status status, LocalDate startDate, LocalDate endDate, Boolean verified, Long petId, Long userId, Pageable pageable);
}

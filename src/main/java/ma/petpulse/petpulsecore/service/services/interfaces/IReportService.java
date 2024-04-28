package ma.petpulse.petpulsecore.service.services.interfaces;

import ma.petpulse.petpulsecore.dao.entities.Report;
import ma.petpulse.petpulsecore.service.dtos.ReportDto;

import java.util.List;

public interface IReportService {
    ReportDto saveReport(ReportDto reportDto);
    ReportDto updateReport(Long reportId,Report reportDto);
    void deleteReport(Long reportId);
    ReportDto getReportById(Long reportId);
    List<ReportDto> getAllReports();
    List<ReportDto> getReportsByType(String type);
    List<ReportDto> getReportsByUserId(Long userId);
}

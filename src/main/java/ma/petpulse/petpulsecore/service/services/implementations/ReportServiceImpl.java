package ma.petpulse.petpulsecore.service.services.implementations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.petpulse.petpulsecore.dao.entities.Report;
import ma.petpulse.petpulsecore.dao.repositories.ReportRepository;
import ma.petpulse.petpulsecore.service.dtos.ReportDto;
import ma.petpulse.petpulsecore.service.mappers.ReportMapper;
import ma.petpulse.petpulsecore.service.services.interfaces.IReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ReportServiceImpl implements IReportService {
    public ReportRepository reportRepository;
public ReportMapper reportMapper;
    @Override
    public ReportDto saveReport(ReportDto reportDto) {
        log.info("saving new report");
        Report report= reportMapper.fromReportDto(reportDto);
        Report savedReport=reportRepository.save(report);
        return reportMapper.fromReport(savedReport);
    }
    @Override
    public ReportDto updateReport(Long reportId, Report reportDto) {
        return null;
    }

    @Override
    public void deleteReport(Long reportId) {

    }

    @Override
    public ReportDto getReportById(Long reportId) {
        return null;
    }

    @Override
    public List<ReportDto> getAllReports() {
        return null;
    }

    @Override
    public List<ReportDto> getReportsByType(String type) {
        return null;
    }

    @Override
    public List<ReportDto> getReportsByUserId(Long userId) {
        return null;
    }
}

package ma.petpulse.petpulsecore.service.mappers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import ma.petpulse.petpulsecore.dao.entities.Report;
import ma.petpulse.petpulsecore.service.dtos.ReportDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ReportMapper {
    public ReportDto fromReport(Report report){
        ReportDto reportDto=new ReportDto();
        BeanUtils.copyProperties(report,reportDto);
        return reportDto;
    }
    public Report fromReportDto(ReportDto reportDto){
        Report report=new Report();
        BeanUtils.copyProperties(reportDto,report);
        return report;
    }
}

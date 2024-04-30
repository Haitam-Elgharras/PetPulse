package ma.petpulse.petpulsecore.service.services.implementations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.Report;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.dao.repositories.ReportRepository;
import ma.petpulse.petpulsecore.exceptions.PetNotFoundException;
import ma.petpulse.petpulsecore.exceptions.ReportNotFoundException;
import ma.petpulse.petpulsecore.exceptions.UserNotFoundException;
import ma.petpulse.petpulsecore.service.dtos.ReportDto;
import ma.petpulse.petpulsecore.service.mappers.ReportMapper;
import ma.petpulse.petpulsecore.service.services.interfaces.IReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ReportServiceImpl implements IReportService {
    public ReportRepository reportRepository;
    public UserServiceImpl userService;
    public PetServiceImpl petService;
    public ReportMapper reportMapper;
    @Override
    public ReportDto saveReport(ReportDto reportDto) throws UserNotFoundException,PetNotFoundException{
        log.info("saving new report");
        Pet pet=petService.getPetById(reportDto.getPet_id());
        User user = userService.getUserById(reportDto.getUser_id());
        if (user==null)
            throw new UserNotFoundException("User with id " + reportDto.getUser_id() + " not found");
        if(pet==null)
            throw new PetNotFoundException("Pet with id "+reportDto.getPet_id()+" not found");
        Report report=reportMapper.fromReportDto(reportDto,pet,user);
        Report savedReport=reportRepository.save(report);
        return reportMapper.fromReport(savedReport);
    }

    @Override
    public ReportDto updateReport(ReportDto reportDto) {
        Pet pet=petService.getPetById(reportDto.getPet_id());
        User user = userService.getUserById(reportDto.getUser_id());
        if (user==null)
            throw new UserNotFoundException("User with id " + reportDto.getUser_id() + " not found");
        if(pet==null)
            throw new PetNotFoundException("Pet with id "+reportDto.getPet_id()+" not found");
        Report report=reportMapper.fromReportDto(reportDto,pet,user);
        Report reportSaved=reportRepository.save(report);
        return reportMapper.fromReport(reportSaved);
    }

    @Override
    public void deleteReport(Long reportId) {
     Report report= reportRepository.findById(reportId).orElse(null) ;
     if (report==null)
         throw new ReportNotFoundException("Report with id"+reportId+" not found");
     reportRepository
             .deleteById(reportId);
    }
    @Override
    public ReportDto getReportById(Long reportId) {
        return reportMapper.fromReport(reportRepository.findById(reportId).orElseThrow(() -> new ReportNotFoundException("Report with id " + reportId+" not found")));
    }
    @Override
    public List<ReportDto> getAllReports() {
        List<Report> reports=reportRepository.findAll();
        List<ReportDto> reportDtos=new ArrayList<>();
        reports.forEach(
                report -> {
                    ReportDto reportDto=reportMapper.fromReport(report);
                    reportDtos.add(reportDto);
                }
        );
        return reportDtos;
    }
    @Override
    public List<ReportDto> getReportsByUserId(Long userId) {
        User user=userService.getUserById(userId);
        if (user==null)
            throw new UserNotFoundException("User with id "+userId+" not found");
        List<Report> reports=reportRepository.findByUserId(userId);
        List<ReportDto> reportDtos=new ArrayList<>();
        reports.forEach(
                report -> {
                    ReportDto reportDto=reportMapper.fromReport(report);
                    reportDtos.add(reportDto);
                }
        );
        return reportDtos;
    }
}

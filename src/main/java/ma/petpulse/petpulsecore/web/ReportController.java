package ma.petpulse.petpulsecore.web;

import lombok.AllArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.Report;
import ma.petpulse.petpulsecore.exceptions.PetNotFoundException;
import ma.petpulse.petpulsecore.exceptions.ReportNotFoundException;
import ma.petpulse.petpulsecore.exceptions.UserNotFoundException;
import ma.petpulse.petpulsecore.service.dtos.PetDto;
import ma.petpulse.petpulsecore.service.dtos.ReportDto;
import ma.petpulse.petpulsecore.service.mappers.ReportMapper;
import ma.petpulse.petpulsecore.service.services.implementations.ReportServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor

public class ReportController {
    private ReportServiceImpl reportService;
    private ReportMapper reportMapper;
    @GetMapping("/reports")
    public List<ReportDto> reports(){
        return reportService.getAllReports();
    }
    @GetMapping("/reports/{id}")
    public ReportDto getReportById(@PathVariable Long id ){
       return reportService.getReportById(id);
    }

    @DeleteMapping("/reports/{id}")
    public void deleteReport(@PathVariable Long id ){
        reportService.deleteReport(id);
    }
    @GetMapping("/reports/user/{userId}")
    public List<ReportDto> getReportsByUserId(@PathVariable Long userId ){
        return reportService.getReportsByUserId(userId);
    }
    @PostMapping("/reports")
    public ResponseEntity<ReportDto> addReport(@Valid @RequestBody ReportDto reportDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReportDto savedReportDto = reportService.saveReport(reportDto);
        return new ResponseEntity<>(savedReportDto, HttpStatus.CREATED);
    }
    @PutMapping("/reports")
    public ResponseEntity<ReportDto> updateReport(@Valid @RequestBody ReportDto reportDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReportDto updatedReportDto = reportService.updateReport(reportDto);
        return new ResponseEntity<>(updatedReportDto, HttpStatus.CREATED);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<String> handleReportNotFound(ReportNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("report not found");
    }
    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<String> handlePetNotFound(PetNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
    }
}

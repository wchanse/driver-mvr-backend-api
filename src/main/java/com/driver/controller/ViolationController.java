package com.driver.controller;

import com.driver.model.Violation;
import com.driver.model.dto.DriverDetails;
import com.driver.model.dto.ViolationDto;
import com.driver.repository.ViolationRepository;
import com.driver.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/violations")
public class ViolationController {

    private final ViolationService violationService;

    @Autowired
    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @GetMapping
    public ResponseEntity<List<ViolationDto>> getViolations(){
        List<Violation> violations = violationService.getViolations();
        List<ViolationDto> violationDto = violations.stream().map(ViolationDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(violationDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ViolationDto> getViolation(@PathVariable final Long id){
        Violation violation = violationService.getViolation(id);
        return new ResponseEntity<>(ViolationDto.from(violation), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ViolationDto> deleteViolation(@PathVariable final Long id){
        Violation violation = violationService.deleteViolation(id);
        return new ResponseEntity<>(ViolationDto.from(violation), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ViolationDto> editViolation(@PathVariable final Long id,
                                                 @RequestBody final ViolationDto violationDto){
        Violation editedViolation = violationService.editViolation(id, Violation.from(violationDto));
        return new ResponseEntity<>(ViolationDto.from(editedViolation), HttpStatus.OK);
    }

    @GetMapping(value = "/stats")
    public List<DriverDetails> getDriverDetails() {
        List<DriverDetails> list = new ArrayList<>();
        List<Object[]> driverDetails = violationService.getDriverDetails();
        driverDetails.forEach(driver -> {
            DriverDetails dto = new DriverDetails();
            dto.setCount(driver[0] + "");
            dto.setDrivers(Stream.of(driver[1].toString().split(",")).collect(Collectors.toList()));
            list.add(dto);
        });
        return list;
    }
}

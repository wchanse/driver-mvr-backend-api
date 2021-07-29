package com.driver.controller;

import com.driver.model.Violation;
import com.driver.model.dto.ViolationDto;
import com.driver.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/violations")
public class ViolationController {

    private final ViolationService violationService;

    @Autowired
    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @PostMapping
    public ResponseEntity<ViolationDto> addViolation(@RequestBody final ViolationDto violationDto){
        Violation violation = violationService.addViolation(Violation.from(violationDto));
        return new ResponseEntity<>(ViolationDto.from(violation), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ViolationDto>> getViolations(){
        List<Violation> violations = violationService.getViolations();
        List<ViolationDto> violationDto = violations.stream().map(ViolationDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(violationDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ViolationDto> getViolation(@PathVariable final Long id){
        Violation violation = violationService.getViolation(id);
        return new ResponseEntity<>(ViolationDto.from(violation), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ViolationDto> deleteViolation(@PathVariable final Long id){
        Violation violation = violationService.deleteViolation(id);
        return new ResponseEntity<>(ViolationDto.from(violation), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ViolationDto> editViolation(@PathVariable final Long id,
                                                 @RequestBody final ViolationDto violationDto){
        Violation editedViolation = violationService.editViolation(id, Violation.from(violationDto));
        return new ResponseEntity<>(ViolationDto.from(editedViolation), HttpStatus.OK);
    }
}

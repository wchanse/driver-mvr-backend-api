package com.driver.model;

import com.driver.model.dto.ViolationDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String violationType;
    private String violationSeverity;
    private Integer violationYear;
    @ManyToOne
    private Driver driver;

    public static Violation from(ViolationDto violationDto){
        Violation violation = new Violation();
        violation.setDescription(violationDto.getDescription());
        violation.setViolationType(violationDto.getViolationType());
        return violation;
    }
}

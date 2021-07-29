package com.driver.model.dto;

import com.driver.model.Violation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

@Data
public class ViolationDto {
    @ApiModelProperty(hidden = true)
    private Long id;
    private String description;
    private String violationType;
    @ApiModelProperty(hidden = true)
    private PlainDriverDto plainDriverDto;

    public static ViolationDto from(Violation violation){
        ViolationDto violationDto = new ViolationDto();
        violationDto.setId(violation.getId());
        violationDto.setDescription(violation.getDescription());
        violationDto.setViolationType(violation.getViolationType());
        if(Objects.nonNull(violation.getDriver())){
            violationDto.setPlainDriverDto(PlainDriverDto.from(violation.getDriver()));
        }
        return violationDto;
    }
}

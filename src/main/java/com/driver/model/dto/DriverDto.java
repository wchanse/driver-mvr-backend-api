package com.driver.model.dto;

import com.driver.model.Driver;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DriverDto {
    @ApiModelProperty(hidden = true)
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String zip;
    private String licenseNumber;
    private String gender;
    private Integer age;
    @ApiModelProperty(hidden = true)
    private List<ViolationDto> violationsDto = new ArrayList<>();

    public static DriverDto from(Driver driver){
        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setFirstName(driver.getFirstName());
        driverDto.setLastName(driver.getLastName());
        driverDto.setCity(driver.getCity());
        driverDto.setState(driver.getState());
        driverDto.setZip(driver.getZip());
        driverDto.setLicenseNumber(driver.getLicenseNumber());
        driverDto.setGender(driver.getGender());
        driverDto.setAge(driver.getAge());
        driverDto.setViolationsDto(driver.getViolations().stream().map(ViolationDto::from).collect(Collectors.toList()));
        return driverDto;
    }
}

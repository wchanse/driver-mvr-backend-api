package com.driver.model.dto;

import com.driver.model.Driver;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlainDriverDto {
    @ApiModelProperty(hidden = true)
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String licenseNumber;

    public static PlainDriverDto from(Driver driver){
        PlainDriverDto plainDriverDto = new PlainDriverDto();
        plainDriverDto.setId(driver.getId());
        plainDriverDto.setFirstName(driver.getFirstName());
        plainDriverDto.setLastName(driver.getLastName());
        plainDriverDto.setCity(driver.getCity());
        plainDriverDto.setState(driver.getState());
        plainDriverDto.setLicenseNumber(driver.getLicenseNumber());
        return plainDriverDto;
    }
}

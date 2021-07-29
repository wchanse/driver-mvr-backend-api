package com.driver.model.dto;

import com.driver.model.Driver;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlainDriverDto {
    @ApiModelProperty(hidden = true)
    private Long id;
    private String name;
    private String licenseNumber;

    public static PlainDriverDto from(Driver driver){
        PlainDriverDto plainDriverDto = new PlainDriverDto();
        plainDriverDto.setId(driver.getId());
        plainDriverDto.setName(driver.getName());
        plainDriverDto.setLicenseNumber(driver.getLicenseNumber());
        return plainDriverDto;
    }
}

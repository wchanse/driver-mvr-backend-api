package com.driver.service;

import com.driver.model.Driver;
import com.driver.model.Violation;
import com.driver.model.dto.PlainDriverDto;

import java.util.List;

public interface DriverService {

    Driver addDriver(Driver driver);
    public List<Driver> getDrivers();
    public Driver getDriver(Long id);
    public Driver deleteDriver(Long id);
    Driver editDriver(Long id, Driver driver);
    Driver addViolationToDriver(Long driverId, Violation violation);
    Driver removeViolationFromDriver(Long driverId, Long violationId);
    List<PlainDriverDto> getHighRiskDrivers();

    Driver findDriverByLicenseNumber(String licenseNumber);
    List<Driver> findAllByZip(String zip);
    List<Driver> findAllByCity(String city);
    List<Driver> findAllByState(String state);
    List<Driver> findAllByLastName(String lastName);
    List<Driver> findAllByGender(String gender);
    List<Driver> findAllByAgeLessThan(Integer age);

}

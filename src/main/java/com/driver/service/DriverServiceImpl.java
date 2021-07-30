package com.driver.service;

import com.driver.model.Driver;
import com.driver.model.Violation;
import com.driver.model.dto.PlainDriverDto;
import com.driver.model.exception.DriverNotFoundException;
import com.driver.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final ViolationService violationService;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, ViolationService violationService) {
        this.driverRepository = driverRepository;
        this.violationService = violationService;
    }

    @Override
    public Driver addDriver(Driver driver){
        return driverRepository.save(driver);
    }

    public List<Driver> getDrivers(){
        return StreamSupport
                .stream(driverRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Driver getDriver(Long id){
        return driverRepository.findById(id).orElseThrow(() ->
                new DriverNotFoundException(id));
    }

    @Override
    public Driver deleteDriver(Long id){
        Driver driver = getDriver(id);
        driverRepository.delete(driver);
        return driver;
    }

    @Transactional
    @Override
    public Driver editDriver(Long id, Driver driver){
        Driver driverToEdit = getDriver(id);
        driverToEdit.setFirstName(driver.getFirstName());
        driverToEdit.setLastName(driver.getLastName());
        driverToEdit.setCity(driver.getCity());
        driverToEdit.setState(driver.getState());
        return driverToEdit;
    }

    @Transactional
    @Override
    public Driver addViolationToDriver(Long driverId, Violation violation) {
        Driver driver = getDriver(driverId);
        driver.addItem(violation);
        violation.setDriver(driver);
        return driver;
    }

    @Transactional
    @Override
    public Driver removeViolationFromDriver(Long driverId, Long violationId){
        Driver driver = getDriver(driverId);
        Violation violation = violationService.getViolation(violationId);
        driver.removeItem(violation);
        return driver;
    }

    @Override
    public List<PlainDriverDto> getHighRiskDrivers() {
        List<PlainDriverDto> highRisk = new ArrayList<>();
        List<Driver> driverList = driverRepository.findAll();
        for(Driver driver: driverList) {
            if (driver.getViolations().size() >= 2) {
                // add to high risk
                highRisk.add(PlainDriverDto.from(driver));
            }
        }
        return highRisk;
    }
}

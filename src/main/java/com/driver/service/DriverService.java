package com.driver.service;

import com.driver.model.Driver;
import com.driver.model.Violation;
import com.driver.model.exception.DriverNotFoundException;
import com.driver.model.exception.ViolationIsAlreadyAssignedException;
import com.driver.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final ViolationService violationService;

    @Autowired
    public DriverService(DriverRepository driverRepository, ViolationService violationService) {
        this.driverRepository = driverRepository;
        this.violationService = violationService;
    }

    public Driver addDriver(Driver driver){
        return driverRepository.save(driver);
    }

    public List<Driver> getDrivers(){
        return StreamSupport
                .stream(driverRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Driver getDriver(Long id){
        return driverRepository.findById(id).orElseThrow(() ->
                new DriverNotFoundException(id));
    }

    public Driver deleteDriver(Long id){
        Driver driver = getDriver(id);
        driverRepository.delete(driver);
        return driver;
    }

    @Transactional
    public Driver editDriver(Long id, Driver driver){
        Driver driverToEdit = getDriver(id);
        driverToEdit.setName(driver.getName());
        return driverToEdit;
    }

    @Transactional
    public Driver addViolationToDriver(Long driverId, Long violationId){
        Driver driver = getDriver(driverId);
        Violation violation = violationService.getViolation(violationId);
        if(Objects.nonNull(violation.getDriver())){
            throw new ViolationIsAlreadyAssignedException(violationId,
                    violation.getDriver().getId());
        }
        driver.addItem(violation);
        violation.setDriver(driver);
        return driver;
    }

    @Transactional
    public Driver removeViolationFromDriver(Long driverId, Long violationId){
        Driver driver = getDriver(driverId);
        Violation violation = violationService.getViolation(violationId);
        driver.removeItem(violation);
        return driver;
    }
}

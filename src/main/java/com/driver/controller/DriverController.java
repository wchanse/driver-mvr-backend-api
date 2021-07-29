package com.driver.controller;

import com.driver.model.Driver;
import com.driver.model.dto.DriverDto;
import com.driver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<DriverDto> addDriver(@RequestBody final DriverDto driverDto){
        Driver driver = driverService.addDriver(Driver.from(driverDto));
        return new ResponseEntity<>(DriverDto.from(driver), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DriverDto>> getDrivers(){
        List<Driver> drivers = driverService.getDrivers();
        List<DriverDto> driversDto = drivers.stream().map(DriverDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(driversDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable final Long id){
        Driver driver = driverService.getDriver(id);
        return new ResponseEntity<>(DriverDto.from(driver), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<DriverDto> deleteDriver(@PathVariable final Long id){
        Driver driver = driverService.deleteDriver(id);
        return new ResponseEntity<>(DriverDto.from(driver), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<DriverDto> editDriver(@PathVariable final Long id,
                                              @RequestBody final DriverDto driverDto){
        Driver driver = driverService.editDriver(id, Driver.from(driverDto));
        return new ResponseEntity<>(DriverDto.from(driver), HttpStatus.OK);
    }

    @PostMapping(value = "{driverId}/violations/{violationId}/add")
    public ResponseEntity<DriverDto> addItemToDriver(@PathVariable final Long driverId,
                                                   @PathVariable final Long violationId){
        Driver driver = driverService.addViolationToDriver(driverId, violationId);
        return new ResponseEntity<>(DriverDto.from(driver), HttpStatus.OK);
    }

    @DeleteMapping(value = "{driverId}/violations/{violationId}/remove")
    public ResponseEntity<DriverDto> removeViolationFromDriver(@PathVariable final Long driverId,
                                                        @PathVariable final Long violationId){
        Driver driver = driverService.removeViolationFromDriver(driverId, violationId);
        return new ResponseEntity<>(DriverDto.from(driver), HttpStatus.OK);
    }
}

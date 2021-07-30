package com.driver.model;

import com.driver.model.dto.DriverDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String zip;
    private String licenseNumber;
    private String gender;
    private Integer age;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private List<Violation> violations = new ArrayList<>();

    public void addItem(Violation violation){
        violations.add(violation);
    }

    public void removeItem(Violation violation){
        violations.remove(violation);
    }

    public static Driver from(DriverDto driverDto){
        Driver driver = new Driver();
        driver.setFirstName(driverDto.getFirstName());
        driver.setLastName(driverDto.getLastName());
        driver.setCity(driverDto.getCity());
        driver.setState(driverDto.getState());
        driver.setZip(driverDto.getZip());
        driver.setLicenseNumber(driverDto.getLicenseNumber());
        driver.setGender(driverDto.getGender());
        driver.setAge(driverDto.getAge());
        return driver;
    }
}

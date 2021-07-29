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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String licenseNumber;
    @OneToMany(
            cascade = CascadeType.ALL
    )
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
        driver.setName(driverDto.getName());
        driver.setLicenseNumber(driverDto.getLicenseNumber());
        return driver;
    }
}

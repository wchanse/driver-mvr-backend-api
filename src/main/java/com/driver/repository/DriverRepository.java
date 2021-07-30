package com.driver.repository;

import com.driver.model.Driver;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Override
    List<Driver> findAll();
    Driver findDriverById(Long id);
    Driver findDriverByLicenseNumber(String licenseNumber);
    List<Driver> findAllByZip(String zip);
    List<Driver> findAllByCity(String city);
    List<Driver> findAllByState(String state);
    List<Driver> findAllByLastName(String lastName);
    List<Driver> findAllByGender(String gender);
    List<Driver> findAllByAgeLessThan(Integer age);


//    Driver findDriverByLastName(String lastName);
}

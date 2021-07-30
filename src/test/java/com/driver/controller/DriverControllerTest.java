package com.driver.controller;

import com.driver.model.Driver;
import com.driver.model.dto.DriverDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DriverControllerTest {

    private TestRestTemplate restTemplate;

    @Autowired
    public DriverControllerTest(TestRestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Test
    public void driverCreatedTest(){

        Driver driver = new Driver();
        driver.setId(2L);
        driver.setLastName("Doe");
        driver.setFirstName("John");
        driver.setCity("San Francisco");
        driver.setState("CA");
        driver.setLicenseNumber("4GJS839");

        Driver response = this.restTemplate.postForObject("/api/v1/drivers/", driver, Driver.class );
//        Driver response = this.restTemplate.getForObject("/api/drivers/", Driver.class);
        System.out.println(response);

        Assertions.assertEquals(driver, response);


    }
}

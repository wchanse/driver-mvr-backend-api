package com.driver.controller;
import com.driver.model.Driver;
import com.driver.model.dto.DriverDto;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DriverControllerTestIT {

    private TestRestTemplate restTemplate;

    @Autowired
    public DriverControllerTestIT(TestRestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Test
    public void driverCreatedTestIT(){

        Driver driver = new Driver();
        driver.setId(4L);
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

    @Test
    public void driverGetAll() throws JSONException {

        String response = this.restTemplate.getForObject("/api/v1/drivers/", String.class );
//        Driver response = this.restTemplate.getForObject("/api/drivers/", Driver.class);
        System.out.println(response);

        JSONAssert.assertEquals("[{id: 1}, {id: 2}, {id:3}, {id:4}]", response, true);


    }
}



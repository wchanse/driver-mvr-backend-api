package com.driver;

import com.driver.model.Driver;

public class TestUtil {


    public static Driver createValidDriver() {

        Driver driver = new Driver();
        driver.setFirstName("test-firstname");
        driver.setLastName("test-lastname");
        driver.setCity("test-city");
        driver.setLastName("test-state");
        driver.setLicenseNumber("123");

        return driver;
    }
}

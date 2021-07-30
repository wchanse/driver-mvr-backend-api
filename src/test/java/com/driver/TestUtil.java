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
/*
    public static User createValidUser(String username) {
        User user = createValidUser();
        user.setUsername(username);
        return user;
    }

    public static Hoax createValidHoax() {
        Hoax hoax = new Hoax();
        hoax.setContent("test content for the test hoax");
        return hoax;


    }*/
}

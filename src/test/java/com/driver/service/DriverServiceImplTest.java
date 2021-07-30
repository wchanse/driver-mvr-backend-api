package com.driver.service;

import com.driver.TestUtil;
import com.driver.model.Driver;
import com.driver.repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

    @InjectMocks
    DriverServiceImpl driverService;

    @Mock
    DriverRepository driverRepository;

    @Mock
    ViolationService violationService;

    @Test
    public void postDriver_whenDriverIsValid_returnsDriver() {
        Driver driver = TestUtil.createValidDriver();

        when(driverRepository.save(driver)).thenReturn(driver);
        Driver returnedDriver = driverService.addDriver(driver);

        assertThat(returnedDriver).isEqualTo(driver);
    }

}
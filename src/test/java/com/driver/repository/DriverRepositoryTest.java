package com.driver.repository;

import com.driver.TestUtil;
import com.driver.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class DriverRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    DriverRepository driverRepository;

    @BeforeEach
    public void cleanUp() {
        driverRepository.deleteAll();
    }

    @Test
    public void findById_whenDriverExists_returnsDriver() {
        testEntityManager.persist(TestUtil.createValidDriver());

        Driver inDB = driverRepository.findById(1L).orElse(null);
        assertThat(inDB).isNotNull();
    }

    @Test
    public void findById_whenDriverNotExist_returnNull() {
        Driver inDB = driverRepository.findById(1L).orElse(null);
        assertThat(inDB).isNull();
    }
}
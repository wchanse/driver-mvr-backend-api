package com.driver.repository;

import com.driver.model.Violation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolationRepository extends JpaRepository<Violation, Long> {

    @Query(value = "select count(*), (select lastName from drivers.Driver where id = driver_id) driver from drivers.Violation v group by (driver_id)",
            nativeQuery = true)
    List<Object[]> getDriverDetails();
}

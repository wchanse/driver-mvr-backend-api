package com.driver.service;

import com.driver.model.Violation;

import java.util.List;

public interface ViolationService {

    List<Violation> getViolations();
    Violation getViolation(Long id);
    Violation deleteViolation(Long id);
    Violation editViolation(Long id, Violation violation);

}

package com.driver.service;

import com.driver.model.Violation;
import com.driver.model.exception.ViolationNotFoundException;
import com.driver.repository.ViolationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ViolationServiceImpl implements ViolationService {

    private final ViolationRepository violationRepository;

    @Autowired
    public ViolationServiceImpl(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    public List<Violation> getViolations(){
        return StreamSupport
                .stream(violationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Violation getViolation(Long id){
        return violationRepository.findById(id).orElseThrow(() ->
                new ViolationNotFoundException(id));
    }

    public Violation deleteViolation(Long id){
        Violation violation = getViolation(id);
        violationRepository.delete(violation);
        return violation;
    }

    @Transactional
    public Violation editViolation(Long id, Violation violation){
        Violation violationToEdit = getViolation(id);
        violationToEdit.setDescription(violation.getDescription());
        return violationToEdit;
    }

    @Override
    public List<Object[]> getDriverDetails() {
        return violationRepository.getDriverDetails();
    }
}

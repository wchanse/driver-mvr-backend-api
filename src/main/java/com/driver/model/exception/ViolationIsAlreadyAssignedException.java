package com.driver.model.exception;

import java.text.MessageFormat;

public class ViolationIsAlreadyAssignedException extends RuntimeException{
    public ViolationIsAlreadyAssignedException(final Long violationId, final Long driverId){
        super(MessageFormat.format("Violation: {0} is already assigned to driver: {1}", violationId, driverId));
    }
}

package com.driver.model.exception;

import java.text.MessageFormat;

public class ViolationNotFoundException extends RuntimeException {

    public ViolationNotFoundException(final Long id){
        super(MessageFormat.format("Could not find violation with id: {0}", id));
    }
}

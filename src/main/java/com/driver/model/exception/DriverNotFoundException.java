package com.driver.model.exception;

import java.text.MessageFormat;

public class DriverNotFoundException extends RuntimeException {

    public DriverNotFoundException(final Long id){
        super(MessageFormat.format("Could not find driver with id: {0}", id));
    }

}

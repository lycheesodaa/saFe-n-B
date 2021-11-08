package com.cs203.project.timeslot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TimeslotExistsException extends RuntimeException {
    public TimeslotExistsException() {
        super("Timeslot already exists. Update or find another date.");
    }
}

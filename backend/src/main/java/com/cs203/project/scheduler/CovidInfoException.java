package com.cs203.project.scheduler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class CovidInfoException extends RuntimeException {
    public CovidInfoException() {
        super("Could not retrive COVID-19 data, please try again.");
    }
}

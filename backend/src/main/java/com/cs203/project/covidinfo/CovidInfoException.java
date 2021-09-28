package com.cs203.project.covidinfo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CovidInfoException extends RuntimeException {
    public CovidInfoException(String message) {
        super(message);
    }
}

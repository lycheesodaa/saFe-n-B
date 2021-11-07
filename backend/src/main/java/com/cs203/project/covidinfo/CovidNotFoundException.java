package com.cs203.project.covidinfo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CovidNotFoundException extends RuntimeException {
    public CovidNotFoundException() {
        super("No Covid reports found.");
    }

    // public CovidNotFoundException(LocalDate date) {
    //     super("Daily report on " + date.toString() + " not found.");
    // }

    // public CovidNotFoundException(LocalDate start, LocalDate end) {
    //     super("Covid reports from " + start.toString() + " to " + end.toString() + " not found.");
    // }
}

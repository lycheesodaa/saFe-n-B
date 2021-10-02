package com.cs203.project.covidinfo;

import java.time.LocalDate;
import java.util.List;

public interface CovidService {
    List<Covid> listCovids();
//    Covid getCovid(LocalDate date);
//    List<Covid> getCovidBetween(LocalDate start, LocalDate end);
    Covid saveCovid(Covid covid);
//    long getTotalCases(LocalDate date);
}

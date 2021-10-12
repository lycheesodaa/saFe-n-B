package com.cs203.project.covidinfo;

import java.time.LocalDate;
import java.util.List;

public interface CovidService {
    List<Covid> listCovids();
    Covid getLatestCovid();
    Covid saveCovid(Covid covid);
}

//Covid getCovid(LocalDate date);
//List<Covid> getCovidBetween(LocalDate start, LocalDate end);
//long getTotalCases(LocalDate date);
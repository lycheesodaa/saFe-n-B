package com.cs203.project.covidinfo;

import java.util.Date;
import java.util.List;

public interface CovidService {
    List<Covid> listCovids();
    Covid getCovid(Date id);
    List<Covid> getCovidBetween(Date start, Date end);
    Covid addCovid(Covid covid);
    // Covid updateCovid(Date id, Covid covid);
    long getTotalCases(Date id);
}

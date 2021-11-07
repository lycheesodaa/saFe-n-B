package com.cs203.project.covidinfo;

import java.util.List;

public interface CovidService {
    List<Covid> listCovids();
    Covid getLatestCovid();
    Covid saveCovid(Covid covid);
}
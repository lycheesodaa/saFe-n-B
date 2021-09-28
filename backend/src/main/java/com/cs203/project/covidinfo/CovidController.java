package com.cs203.project.covidinfo;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidController {
    private CovidService covidService;

    public CovidController(CovidService cs) {
        this.covidService = cs;
    }

    /**
     * List all Covid reports in the system by day.
     * @return a list of daily Covid reports
     */
    @GetMapping("/covids")
    public List<Covid> getCovids() {
        return covidService.listCovids();
    }

    /**
     * Returns a daily Covid report
     * @param id - the date of the report
     * @return the Covid report of the date specified
     */
    @GetMapping("/covids/{id}")
    public Covid getCovid(Date id) {
        return covidService.getCovid(id);
    }

    /**
     * Return all Covid reports between the specified dates
     * @param start - start date
     * @param end - end date
     * @return the Covid reports between the dates specified
     */
    @GetMapping(value = "/covids/dates", params = {"start", "end"})
    public List<Covid> getCovidBetween(Date start, Date end) {
        return covidService.getCovidBetween(start, end);
    }

    
}

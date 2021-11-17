package com.cs203.project.covidinfo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class CovidController {
    private CovidService covidService;

    public CovidController(CovidService cs) {
        this.covidService = cs;
    }

    /**
     * List all Covid reports in the system by day.
     * @return a list of daily Covid reports
     * @exception CovidNotFoundException if there are no reports
     */
    @GetMapping("/covids")
    public List<Covid> getCovids() {
        List<Covid> cids = covidService.listCovids();
        return cids;
    }
    
    /**
     * Get the latest Covid report.
     * @return the latest Covid reports
     * @exception CovidNotFoundException if there are no reports
     */
    @GetMapping("/covid")
    public Covid getLatestCovid() {
        Covid latestCovid = covidService.getLatestCovid();
        if (latestCovid == null) throw new CovidNotFoundException();
        return latestCovid;
    }

}



/**
* Returns a daily Covid report
* @param id - the date of the report
* @return the Covid report of the date specified
* @exception CovidNotFoundException if there are no reports for the specified date
*/
//@GetMapping("/covids/{date}")
//public Covid getCovid(LocalDate date) {
//    Covid covid = covidService.getCovid(date);
//    if (covid == null) {
//        throw new CovidNotFoundException(date);
//    }
//    return covid;
//}

/**
* Return all Covid reports between the specified dates
* @param start - start date
* @param end - end date
* @return the Covid reports between the dates specified
* @exception CovidNotFoundException if there are no reports between the specified dates
*/
//@GetMapping(value = "/covids/dates", params = {"start", "end"})
//public List<Covid> getCovidBetween(LocalDate start, LocalDate end) {
//    List<Covid> cids = covidService.getCovidBetween(start, end);
//    if (cids == null) throw new CovidNotFoundException();
//    return cids;
//}

/**
* Returns the total number of cases, before and including the specified date
* @param id - the date to be specified
* @return the total number of cases
*/
//@GetMapping("/covids/totalCases/{date}")
//public long getTotalCases(LocalDate date) {
//    return covidService.getTotalCases(date);
//}

/**
* Adding a report to the repository, should always work.
* @param covid - the report to be added
* @return the added report
*/
//@PostMapping("/covids")
//public Covid addCovid(Covid covid) {
//    return covidService.saveCovid(covid);
//}

/**
* Same as {@code addCovid} but using a HTTP {@code PUT} mapping
* @param covid
* @return
*/
//@PutMapping("/covids")
//public Covid updateCovid(Covid covid) {
//    return covidService.saveCovid(covid);
//}

// no delete mapping because there is no need for it

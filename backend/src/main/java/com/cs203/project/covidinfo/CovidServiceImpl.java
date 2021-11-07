package com.cs203.project.covidinfo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CovidServiceImpl implements CovidService {

    private CovidRepository covidInfo;

    public CovidServiceImpl(CovidRepository covidInfo) {
        this.covidInfo = covidInfo;
    }

    @Override
    public List<Covid> listCovids() {
        return covidInfo.findAll();
    }
    
    @Override
    public Covid getLatestCovid() {
    	List<Covid> covids = covidInfo.findAll();
        if (covids.size() == 0) {
            return null;
        }

    	Covid latest = covids.get(covids.size() - 1);
    	return latest;
    }

    /**
     * Immediately overwrites the existing data since it
     * will only be called in CovidScraper.java
     * 
     * @return Updated information based on website
     */
    @Override
    public Covid saveCovid(Covid covid) {
        return covidInfo.save(covid);
    }



}

//@Override
//public Covid getCovid(LocalDate date) {
//  return covidInfo.findByDate(date).orElse(null);
//}

//@Override
//public List<Covid> getCovidBetween(LocalDate start, LocalDate end) {
//  return covidInfo.findByDateBetween(start, end);
//}

//@Override
//public long getTotalCases(LocalDate date) {
//  return covidInfo.getTotalCases(date);
//}

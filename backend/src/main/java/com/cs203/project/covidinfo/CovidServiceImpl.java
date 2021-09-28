package com.cs203.project.covidinfo;

import java.util.Date;
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
    public Covid getCovid(Date id) {
        return covidInfo.findById(id).orElse(null);
    }

    @Override
    public List<Covid> getCovidBetween(Date start, Date end) {
        return covidInfo.findByIdBetween(start, end);
    }

    /**
     * Immediately overwrites the existing data since it
     * will only be called in ScheduledTask.java
     * 
     * @return Updated information based on website
     */
    @Override
    public Covid addCovid(Covid covid) {
        return covidInfo.save(covid);
    }

    // @Override
    // public Covid updateCovid(Date id, Covid newCovid) {
    //     return covidInfo.findById(id).map(covid -> {
    //         covid.setNewCases(newCovid.getNewCases());
    //         covid.setTotalDoses(newCovid.getTotalDoses());
    //         covid.setFullDose(newCovid.getFullDose());
    //         covid.setOneDose(newCovid.getOneDose());
    //         covid.setHospitalised(newCovid.getHospitalised());
    //         covid.setCritical(newCovid.getCritical());
    //         covid.setDeaths(newCovid.getDeaths());

    //         return covidInfo.save(covid);
    //     }).orElse(null);
    // }

    @Override
    public long getTotalCases(Date id) {
        return covidInfo.getTotalCases(id);
    }

}

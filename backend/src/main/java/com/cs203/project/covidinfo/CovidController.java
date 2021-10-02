package com.cs203.project.covidinfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidController {
    private CovidService covidService;

    public CovidController(CovidService cs) {
        this.covidService = cs;
    }
    
    
    @GetMapping("/scrape")
    public String test() {
		try {
			Covid covid = new Covid();
		    Document document= Jsoup.connect("https://againstcovid19.com/singapore/dashboard").get();
		    Elements elements = document.getElementsByClass("tx-normal tx-rubik mg-b-0 mg-r-5 lh-1");
		    String totalCases = elements.get(0).text();
		    String activeCases = elements.get(1).text();
		    String deceased = elements.get(2).text();
		    String discharged = elements.get(3).text();
		    
		    String critical = document.getElementsByClass("tx-medium tx-danger").get(0).text();
		    
		    Elements genderAndImported = document.getElementsByClass("tx-normal tx-rubik tx-spacing--2 lh-1 mg-b-5");
		    String maleCases = genderAndImported.get(0).ownText();
		    String femaleCases = genderAndImported.get(1).ownText();
		    String genderUnidentifiedCases = genderAndImported.get(2).ownText();
		    String localTransmissions = genderAndImported.get(3).ownText();
		    String importedCases = genderAndImported.get(4).ownText();
		    String importedOrLocalUnreportedCases = genderAndImported.get(5).ownText();
		    String averageAge = document.getElementsByClass("tx-20 tx-sm-18 tx-md-24 tx-normal tx-rubik mg-b-0").first().text();
 
		    Elements naionalityAndInfection = document.getElementsByClass("col-12 tx-12 mg-t-40");
		    Element nationality = naionalityAndInfection.get(0);
		    Element infectionSources = naionalityAndInfection.get(1);
		    Elements nationalityElements = nationality.getElementsByClass("d-flex align-items-center");
		    ArrayList<Nationality> nationalityList = new ArrayList();
		    for (Element e: nationalityElements) {
		    	String nation = e.getElementsByClass("tx-medium mg-l-10").get(0).text();
		    	String number = e.getElementsByClass("tx-rubik mg-l-auto").get(0).text();
//		    	String percentage = e.getElementsByClass("wd-60 tx-right tx-rubik mg-l-5 tx-success").get(0).text();
		    	Nationality n = new Nationality();
		    	n.setNationality(nation);
		    	n.setNumber(Long.valueOf(number.replace(",", "")));
		    	n.setCovid(covid);
		    	nationalityList.add(n);
		    }
		    
		    
		    Elements infectionSourcesElements = infectionSources.getElementsByClass("d-flex align-items-center");
		    ArrayList<InfectionSource> infectionSourceList = new ArrayList();
		    for (Element e: infectionSourcesElements) {
		    	String infectionSource = e.getElementsByClass("tx-medium mg-l-10").get(0).text();
		    	String number = e.getElementsByClass("tx-rubik mg-l-auto").get(0).text();
//		    	String percentage = e.getElementsByClass("wd-60 tx-right tx-rubik mg-l-5 tx-danger").get(0).text();
		    	InfectionSource i = new InfectionSource();
		    	i.setInfectionSource(infectionSource);
		    	i.setNumber(Long.valueOf(number.replace(",", "")));
		    	i.setCovid(covid);
		    	infectionSourceList.add(i);
		    }
		    Element table = document.getElementsByClass("table table-borderless table-dashboard table-dashboard-one").first();
		    Element tableBody = table.getElementsByTag("tbody").first();
		    Elements tableRows = tableBody.getElementsByTag("tr");
		    ArrayList<Cluster> clusterList = new ArrayList();
		    for (Element row: tableRows) {
		    	String clusterName = row.getElementsByClass("tx-medium").first().text();
		    	String clusterNumber = row.getElementsByClass("text-right").first().text();
		    	Cluster c = new Cluster();
		    	c.setCluster(clusterName);
		    	c.setNumber(Long.valueOf(clusterNumber.replace(",", "")));
		    	c.setCovid(covid);
		    	clusterList.add(c);
		    }
		    
		    covid.setDate(new Date());
		    covid.setTotalCases(Long.valueOf(totalCases.replace(",", "")));
		    covid.setActiveCases(Long.valueOf(activeCases.replace(",", "")));
		    covid.setDeceased(Long.valueOf(deceased.replace(",", "")));
		    covid.setDischarged(Long.valueOf(discharged.replace(",", "")));
		    covid.setCritical(Long.valueOf(critical.replace(",", "")));
		    covid.setMaleCases(Long.valueOf(maleCases.replace(",", "")));
		    covid.setFemaleCases(Long.valueOf(femaleCases.replace(",", "")));
		    covid.setGenderUnidentifiedCases(Long.valueOf(genderUnidentifiedCases.replace(",", "")));
		    covid.setLocalTransmissions(Long.valueOf(localTransmissions.replace(",", "")));
		    covid.setImportedCases(Long.valueOf(importedCases.replace(",", "")));
		    covid.setImportedOrLocalUnreportedCases(Long.valueOf(importedOrLocalUnreportedCases.replace(",", "")));
		    covid.setAverageAge(Long.valueOf(averageAge.replace(",", "")));
		    covid.setNationalityList(nationalityList);
		    covid.setInfectionSourceList(infectionSourceList);
		    covid.setClusterList(clusterList);
		    covidService.saveCovid(covid);
		    return "YAY";
		} catch (IOException e){
		    e.getMessage(); 
		    return null;
		}
    }

    // /**
    //  * List all Covid reports in the system by day.
    //  * @return a list of daily Covid reports
    //  * @exception CovidNotFoundException if there are no reports
    //  */
    // @GetMapping("/covids")
    // public List<Covid> getCovids() {
    //     List<Covid> cids = covidService.listCovids();
    //     if (cids == null) throw new CovidNotFoundException();
    //     return cids;
    // }

    // /**
    //  * Returns a daily Covid report
    //  * @param id - the date of the report
    //  * @return the Covid report of the date specified
    //  * @exception CovidNotFoundException if there are no reports for the specified date
    //  */
    // @GetMapping("/covids/{date}")
    // public Covid getCovid(LocalDate date) {
    //     Covid covid = covidService.getCovid(date);
    //     if (covid == null) {
    //         throw new CovidNotFoundException(date);
    //     }
    //     return covid;
    // }

    // /**
    //  * Return all Covid reports between the specified dates
    //  * @param start - start date
    //  * @param end - end date
    //  * @return the Covid reports between the dates specified
    //  * @exception CovidNotFoundException if there are no reports between the specified dates
    //  */
    // @GetMapping(value = "/covids/dates", params = {"start", "end"})
    // public List<Covid> getCovidBetween(LocalDate start, LocalDate end) {
    //     List<Covid> cids = covidService.getCovidBetween(start, end);
    //     if (cids == null) throw new CovidNotFoundException();
    //     return cids;
    // }

    // /**
    //  * Returns the total number of cases, before and including the specified date
    //  * @param id - the date to be specified
    //  * @return the total number of cases
    //  */
    // @GetMapping("/covids/totalCases/{date}")
    // public long getTotalCases(LocalDate date) {
    //     return covidService.getTotalCases(date);
    // }

    // /**
    //  * Adding a report to the repository, should always work.
    //  * @param covid - the report to be added
    //  * @return the added report
    //  */
    // @PostMapping("/covids")
    // public Covid addCovid(Covid covid) {
    //     return covidService.saveCovid(covid);
    // }

    // /**
    //  * Same as {@code addCovid} but using a HTTP {@code PUT} mapping
    //  * @param covid
    //  * @return
    //  */
    // @PutMapping("/covids")
    // public Covid updateCovid(Covid covid) {
    //     return covidService.saveCovid(covid);
    // }

    // no delete mapping because there is no need for it
}

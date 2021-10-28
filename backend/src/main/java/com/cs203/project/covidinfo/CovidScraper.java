package com.cs203.project.covidinfo;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CovidScraper {
	
    private CovidService covidService;

    public CovidScraper(CovidService cs) {
        this.covidService = cs;
    }
	
    public String regulationsScrape() {
    	try {
    	Document document = Jsoup.connect("https://www.enterprisesg.gov.sg/covid-19/safe-distance").get();
    	Element fb_div = document.getElementById("FB");
    	Element walk = fb_div;
    	String content = "";
    	while (!walk.nextElementSibling().hasAttr("id") && walk.nextElementSibling().attr("id") != "RE") {
    		content += walk.wholeText();
    		walk = walk.nextElementSibling();
    	}
    		return content;
    	} catch (Exception e) {
    		return e.getMessage();
    	}
    }

//second, minute, hour, day, month, weekday
	@Scheduled(cron = "0 0 0 * * *")
//	@PostConstruct
    public void test() {
		System.out.println("Starting web scraping");
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
		    covid.setRegulations(regulationsScrape());
		    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
//		    covid.setDate(dtf.format(LocalDate.now()));
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
		    System.out.println("Web scraping done and covid information saved successfully.");
		} catch (IOException e){
		    e.getMessage();
		}
    }
}


//public String regulations() {
//try {
//	final WebClient webClient = new WebClient();
//	webClient.getOptions().setThrowExceptionOnScriptError(false);
//	final HtmlPage page = webClient.getPage("https://www.moh.gov.sg/covid-19-phase-advisory/phase-3-sector-related-advisories");
//	List<HtmlAnchor> anchors = page.getAnchors();;
//	HtmlAnchor regulationsAnchor = null;
//	for (HtmlAnchor anchor: anchors) {
//		if (anchor.getTextContent().contains("Updated Advisory for Safe Management Measures at Food & Beverage Establishments")) {
//			regulationsAnchor = anchor;
//			break;
//		}
//	}
//	String newWebPageURL = regulationsAnchor.getHrefAttribute();
//	String result = scrapeNewPage(newWebPageURL);
//	return result;
//} catch(Exception e) {
//	return e.getMessage();
//}
//}
//
//public String scrapeNewPage(String newWebPageURL) {
//try {
//	Document document= Jsoup.connect(newWebPageURL).get();
//	Element article_body = document.getElementsByClass("article-body").first();
//	Element anchor = article_body.select("a").first();
////	return extractText("https://www.enterprisesg.gov.sg/" + anchor.attr("href"));
//	return "YAY";
//} catch (Exception e) {
//	return e.getMessage();
//}
//}
//
//
//
//public String readPDFContent() throws Exception {
//URL url = new URL("https://www.enterprisesg.gov.sg//-/media/esg/files/media-centre/media-releases/2021/august/mr05921_updated-advisory-for-safe-management-measures-at-food-beverage-establishments.pdf?la=en");
//InputStream is = url.openStream();
//BufferedInputStream fileToParse = new BufferedInputStream(is);
//PDDocument document = null;
//String output = null;
//try {
//    document = PDDocument.load(fileToParse);
//    output = new PDFTextStripper().getText(document);
//    System.out.println(output);
//} finally {
//    if (document != null) {
//        document.close();
//    }
//    fileToParse.close();
//    is.close();
//}
//return output;
//
//}
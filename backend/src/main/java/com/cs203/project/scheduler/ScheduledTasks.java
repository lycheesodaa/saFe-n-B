package com.cs203.project.scheduler;

import com.cs203.project.covidinfo.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private CovidService covidService;

    private static final WebClient webClient = new WebClient();


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM");

    public ScheduledTasks(CovidService covidService) {
        this.covidService = covidService;
    }

    /**
     * For scraping COVID-19 data directly from MOH's website. This method cannot
     * scrape past data, only data from the current date or before depending on
     * whether it has been updated.
     * 
     * @JSoup Used to connect and retrive website data.
     * 
     * @exception CovidInfoException if website is unreachable.
     */
    // @PostConstruct
    public void scrape() {
        // Document doc = connectionSetup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://covidsitrep.moh.gov.sg/");

        WebElement summaryFrame = driver.findElement(By.cssSelector("#modal>iframe"));

        // scraping the tables
        // table begins at row 5, latest element at 18
        // row 4 contains info from past records
        Element table = doc.select("div.dash-spreadsheet-container.dash-spreadsheet.dash-empty-01.dash-no-filter.dash-fill-width").get(1).selectFirst("table");
        Elements rows = table.select("tr");
        // deathTable begins at 2, latest element at 15 
        // #table-yef2nnbrr2c > div.dash-spreadsheet-container.dash-spreadsheet.dash-empty-01.dash-no-filter.dash-fill-width > div > div.row.row-1 > div.cell.cell-1-1.dash-fixed-content > table
        // cumulative **
        Element deathTable = doc.select("div.dash-spreadsheet-container.dash-spreadsheet.dash-empty-01.dash-no-filter.dash-fill-width").get(2).selectFirst("table");
        Elements deathRows = deathTable.select("tr");

        // Past records contain records from before 14 days ago.
        // ? Covid pastRecords = new Covid();
        Element firstRow = rows.get(4);
        Element firstDeathRow = deathRows.get(2);
        setData(firstRow, firstDeathRow);

        // Selecting rows based on "data-dash-row" values.
        for (int i = 5; i < 19; i++) {
            // ? Covid covid = new Covid();
            Element row = rows.get(i);
            Element deathRow = deathRows.get(i - 3);
            setData(row, deathRow);
        }

        driver.quit();
    }

    /**
     * This method scrapes MOH's website once daily.
     * 
     * {@code @Scheduled} annotation runs the method at 12am daily.
     * 
     * @exception CovidInfoException if website is unreachable.
     */
    // @PostConstruct
    // @Scheduled(cron = "0 0 0 * * *")
    public void dailyScrape() {
        Document doc = connectionSetup();

        Element table = doc.getElementById("table-uqi01msyb4o").selectFirst("table");
        Elements rows = table.select("tr");
        Element row = rows.get(15);
        Element deathTable = doc.getElementById("table-2g4oamh1ril").selectFirst("table");
        Elements deathRows = deathTable.select("tr");
        Element deathRow = deathRows.get(18);

        setData(row, deathRow);
    }

    /**
     * Sets up the connection to MOH's sitrep website.
     * 
     * @return the Document containing the html file to be parsed.
     */
    @PostConstruct
    public Document connectionSetup() {
        try {
            // //HtmlPage scrapePage =  webClient.getPage(("https://covidsitrep.moh.gov.sg/"));
            // //String doc = Jsoup.connect("https://covidsitrep.moh.gov.sg/").get().outerHtml();
            // String doc = webClient.getPage(("https://covidsitrep.moh.gov.sg/")).toString();
            // HtmlPage scrapedPage = webClient.getPage(new File(doc).toURI().toURL());
            // //System.out.println(scrapedPage);
            // return Jsoup.parse(scrapedPage.asXml());


            driver.get("https://covidsitrep.moh.gov.sg/");
            driver.findElement(By.linkText("Summary Table"));
        } catch (IOException e) {
            throw new CovidInfoException();
        }
    }

    /**
     * Creates a new {@code Covid}, assigning the table's elements to it and adding
     * it to the database.
     * 
     * @param row       - the row to be parsed from {@code table}
     * @param deathRows - the row to be parsed from {@code deathTable}
     */
    public void setData(Element row, Element deathRows) {
        Covid covid = new Covid();
        Elements cols = row.select("td");

        // setting a date with a year
        covid.setDate(LocalDate.parse((cols.get(0).text()), formatter).withYear(LocalDate.now().getYear()));
        covid.setImported(Integer.parseInt(cols.get(4).text()));
        covid.setCommunity(Integer.parseInt(cols.get(12).text()));
        covid.setDormitory(Integer.parseInt(cols.get(15).text()));
        covid.setNewCases(Integer.parseInt(cols.get(16).text()));

        Elements deathCols = row.select("td");

        covid.setHospitalised(Integer.parseInt(deathCols.get(2).text()));
        covid.setCritical(Integer.parseInt(deathCols.get(1).text()));
        covid.setDeaths(Integer.parseInt(deathCols.get(6).text()));
        covid.setRecovered(Integer.parseInt(deathCols.get(4).text()) + Integer.parseInt(deathCols.get(5).text()));

        covidService.saveCovid(covid);
    }
}

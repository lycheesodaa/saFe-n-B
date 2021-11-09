package com.cs203.project.covidinfo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CovidScrapeTest {

    @Mock
    private CovidRepository covids;

    @InjectMocks
    private CovidServiceImpl covidService;

    @InjectMocks
    private CovidScraper covidScraper;

    @Test
    void test_RegulationsScrape() {
        String result = covidScraper.regulationsScrape();

        // failing means the website structure changed
        assertNotNull(result);
    }

    @Test
    void test_Scraping() {
        // unimplemented test - too many variables
    }
}

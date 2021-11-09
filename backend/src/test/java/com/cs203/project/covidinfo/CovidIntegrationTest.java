package com.cs203.project.covidinfo;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CovidIntegrationTest {

    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CovidRepository covids;

    @AfterEach
    void tearDown() {
        covids.deleteAll();
    }

    @Test
    public void getCovids_Success() throws Exception {
        URI uri = new URI(baseUrl + port + "/covids");
        covids.save(new Covid("Test", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        covids.save(new Covid("Test 2", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

        ResponseEntity<Covid[]> result = restTemplate.getForEntity(uri, Covid[].class);
        Covid[] covids = result.getBody();

        assertEquals(200, result.getStatusCode().value());
        assertEquals(2, covids.length); // one entry scraped by CovidScraper()
    }

    @Test
    public void getLatestCovid_Success() throws Exception {
        URI uri = new URI(baseUrl + port + "/covid");
        covids.save(new Covid("Test", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        covids.save(new Covid("Test 2", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

        ResponseEntity<Covid> result = restTemplate.getForEntity(uri, Covid.class);
        Covid latestCovid = result.getBody();

        assertEquals(200, result.getStatusCode().value());
        assertEquals("Test 2", latestCovid.getRegulations());
    }
}

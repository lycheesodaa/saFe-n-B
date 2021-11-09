package com.cs203.project.covidinfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CovidServiceTest {
    
    @Mock
    private CovidRepository covids;

    @InjectMocks
    private CovidServiceImpl covidService;

    @Test
    void listAll_NotNull() {
        List<Covid> covidList = covidService.listCovids();

        assertNotNull(covidList);
    }

    @Test
    void addCovid_ReturnSaved() {
        Covid covid = new Covid();
        when(covids.save(any(Covid.class))).thenReturn(covid);

        Covid saved = covidService.saveCovid(covid);

        assertNotNull(saved);
        verify(covids).save(covid);
    }

    @Test
    void getLatestCovid_ReturnLatest() {
        Covid covid1 = new Covid("Test 1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Covid covid2 = new Covid("Test 2", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        ArrayList<Covid> covidList = new ArrayList<>();
        covidList.add(covid1);
        covidList.add(covid2);

        when(covids.findAll()).thenReturn(covidList);

        Covid latestCovid = covidService.getLatestCovid();

        assertNotNull(latestCovid);
        assertEquals("Test 2", latestCovid.getRegulations());
        verify(covids).findAll();
    }

    @Test
    void getLatestCovid_ReturnNull() {
        when(covids.findAll()).thenReturn(new ArrayList<Covid>());

        Covid latestCovid = covidService.getLatestCovid();

        assertNull(latestCovid);
        verify(covids).findAll();
    }
}

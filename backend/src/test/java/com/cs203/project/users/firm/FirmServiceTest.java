package com.cs203.project.users.firm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class FirmServiceTest {
    
    @Mock
    private FirmRepository firms;

    @InjectMocks
    FirmService firmService;

    @Test
    void getAllFirms() {
        List<Firm> eList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            eList.add(new Firm());
        }
        when(firms.findAll()).thenReturn(eList);

        ResponseEntity<List<Firm>> result = firmService.getAllFirms();

        assertEquals(result.getBody().size(), 20);
        verify(firms).findAll();
    }

    @Test
    void getFirm_ReturnFirm() {
        String email = "saladbar@gmail.com";
        Firm emp = new Firm(email, "saladbarney");
        when(firms.findByEmail(email)).thenReturn(emp);

        ResponseEntity<Firm> result = firmService.getFirmByEmail(email);

        assertEquals(result.getBody().getPassword(), "saladbarney");
        verify(firms).findByEmail(email);
    }

    @Test
    void getFirm_Return404() {
        String email = "saladbar@gmail.com";
        when(firms.findByEmail(email)).thenReturn(null);

        ResponseEntity<Firm> result = firmService.getFirmByEmail(email);

        assertNull(result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        verify(firms).findByEmail(email);
    }

    @Test
    void addFirm_ReturnFirm() {
        String email = "saladbar@gmail.com";
        Firm emp = new Firm(email, "saladbarney");
        when(firms.findByEmail(anyString())).thenReturn(null);
        when(firms.save(any(Firm.class))).thenReturn(emp);

        Firm result = firmService.addFirm(emp);

        assertNotNull(result);
        verify(firms).findByEmail(email);
        verify(firms).save(emp);
    }

    @Test
    void addFirm_Exists_ReturnNull() {
        String email = "saladbar@gmail.com";
        Firm emp = new Firm(email, "saladbarney");
        when(firms.findByEmail(anyString())).thenReturn(emp);

        Firm result = firmService.addFirm(emp);

        assertNull(result);
        verify(firms).findByEmail(email);
    }

    @Test
    void updateFirm_ReturnFirm() {
        String email = "saladbar@gmail.com";
        Firm emp = new Firm(email, "saladbarney");
        Firm updated = new Firm(email, "saladbarbells");

        when(firms.findByEmail(anyString())).thenReturn(emp);
        when(firms.save(any(Firm.class))).thenReturn(updated);

        ResponseEntity<Firm> result = firmService.updateFirm(updated);

        assertNotNull(result.getBody());
        verify(firms).findByEmail(email);
        verify(firms).save(updated);
    }

    @Test
    void updateFirm_NotFound_Return404() {
        String email = "saladbar@gmail.com";
        Firm updated = new Firm(email, "saladbarney 2.0");
        when(firms.findByEmail(anyString())).thenReturn(null);

        ResponseEntity<Firm> result = firmService.updateFirm(updated);

        assertNull(result.getBody());
        verify(firms).findByEmail(email);
    }

    @Test
    void deleteFirm_NotFound_Return404() {
        String email = "saladbar@gmail.com";
        when(firms.findByEmail(anyString())).thenReturn(null);

        ResponseEntity<Void> result = firmService.deleteFirm(email);

        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);;
        verify(firms).findByEmail(email);
    }

}

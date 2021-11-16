package com.cs203.project.users.firm;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import com.cs203.project.util.AuthenticationResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FirmIntegrationTest {
    
    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FirmRepository firms;

    @AfterEach
    void tearDown() {
        firms.deleteAll();
    }

    @Test
    public void getFirms_Success() throws Exception {
        URI uri = new URI(baseUrl + port + "/firms");
        String email = "saladbar@gmail.com";
        String password = "saladbarney";
        firms.save(new Firm(email, password));

        ResponseEntity<Firm[]> result = restTemplate.getForEntity(uri, Firm[].class);
        Firm[] FirmList = result.getBody();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, FirmList.length);
    }

    // @Test
    // public void createAuthToken_Success() throws Exception {
    //     URI uri = new URI(baseUrl + port + "/firms");
    //     String email = "saladbar@gmail.com";
    //     String password = "saladbarney";
    //     firms.save(new Firm(email, password).hashingPassword());
    //     AuthenticationRequest authRequest = new AuthenticationRequest(email, password);

    //     ResponseEntity<AuthenticationResponse> result = restTemplate.postForEntity(uri, authRequest,
    //             AuthenticationResponse.class);
    //     AuthenticationResponse authResponse = result.getBody();

    //     assertEquals(200, result.getStatusCodeValue());
    //     assertEquals(authResponse.getUser().getUsername(), email);
    // }

    @Test
    public void getFirm_Success() throws Exception {
        String email = "saladbar@gmail.com";
        String password = "saladbarney";
        URI uri = new URI(baseUrl + port + "/firms/" + email);
        firms.save(new Firm(email, password).hashingPassword());

        ResponseEntity<Firm> result = restTemplate.getForEntity(uri, Firm.class);
        Firm returned = result.getBody();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(email, returned.getEmail());
    }

    // @Test
    // public void addFirm_NewEmail_Success() throws Exception {
    //     String email = "saladbar@gmail.com";
    //     String password = "saladbarney";
    //     Firm newFirm = new Firm(email, password);

    //     URI uri = new URI(baseUrl + port + "/firms");

    //     ResponseEntity<AuthenticationResponse> result = restTemplate.postForEntity(uri, newFirm, AuthenticationResponse.class);
    //     AuthenticationResponse returned = result.getBody();

    //     assertEquals(200, result.getStatusCodeValue());
    //     assertEquals(email, returned.getUser().getUsername());
    // }

    @Test
    public void addFirm_ExistingEmail_Conflict() throws Exception {
        String email = "saladbar@gmail.com";
        String password = "saladbarney";
        Firm newFirm = new Firm(email, password);
        firms.save(newFirm);

        URI uri = new URI(baseUrl + port + "/firms");

        ResponseEntity<AuthenticationResponse> result = restTemplate.postForEntity(uri, newFirm, AuthenticationResponse.class);

        assertEquals(409, result.getStatusCodeValue());
    }

    // @Test
    // public void updateFirm_ValidEmail_Success() throws Exception {
    //     String email = "saladbar@gmail.com";
    //     String password = "saladbarney";
    //     Firm emp = new Firm(email, password);
    //     firms.save(emp.hashingPassword());

    //     Firm newFirm = new Firm(email, "saladmander");

    //     URI uri = new URI(baseUrl + port + "/firms");

    //     ResponseEntity<Firm> result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newFirm),
    //             Firm.class);

    //     assertEquals(200, result.getStatusCodeValue());
    //     assertEquals(newFirm.getPassword(), result.getBody().getPassword());
    // }

    @Test
    public void updateFirm_InvalidEmail_NotFound() throws Exception {
        String email = "saladbar@gmail.com";
        String password = "saladbarney";
        Firm emp = new Firm(email, password);
        firms.save(emp.hashingPassword());

        Firm newFirm = new Firm("saladstall@gmail.com", "saladmander");

        URI uri = new URI(baseUrl + port + "/firms");

        ResponseEntity<Firm> result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newFirm),
                Firm.class);

        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    public void deleteFirm_ValidEmail_Success() throws Exception {
        String email = "saladbar@gmail.com";
        String password = "saladbarney";
        Firm emp = new Firm(email, password);
        firms.save(emp.hashingPassword());

        URI uri = new URI(baseUrl + port + "/firms/" + email);

        ResponseEntity<Void> result = restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);

        assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    public void deleteFirm_InvalidEmail_NotFound() throws Exception {
        String email = "saladbar@gmail.com";

        URI uri = new URI(baseUrl + port + "/firms/" + email);

        ResponseEntity<Void> result = restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);

        assertEquals(404, result.getStatusCodeValue());
    }
}

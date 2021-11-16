package com.cs203.project.users.employee;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import com.cs203.project.users.firm.Firm;
import com.cs203.project.users.firm.FirmRepository;
import com.cs203.project.util.AuthenticationRequest;
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
public class EmployeeIntegrationTest {

    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employees;

    @Autowired
    private FirmRepository firms;

    @AfterEach
    void tearDown() {
        employees.deleteAll();
        firms.deleteAll();
    }

    @Test
    public void getEmployees_Success() throws Exception {
        URI uri = new URI(baseUrl + port + "/employees");
        employees.save(new Employee("bob@gmail.com", "boblovesnoodles"));

        ResponseEntity<Employee[]> result = restTemplate.getForEntity(uri, Employee[].class);
        Employee[] employeeList = result.getBody();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, employeeList.length);
    }

    // @Test
    // public void createAuthToken_Success() throws Exception {
    //     URI uri = new URI(baseUrl + port + "/employees");
    //     String email = "bob@gmail.com";
    //     String password = "boblovesnoodles";
    //     employees.save(new Employee(email, password).hashingPassword());
    //     AuthenticationRequest authRequest = new AuthenticationRequest(email, password);

    //     ResponseEntity<AuthenticationResponse> result = restTemplate.postForEntity(uri, authRequest,
    //             AuthenticationResponse.class);
    //     AuthenticationResponse authResponse = result.getBody();

    //     assertEquals(200, result.getStatusCodeValue());
    //     assertEquals(email, authResponse.getUser().getUsername());
    // }

    @Test
    public void getEmployee_Success() throws Exception {
        String email = "bob@gmail.com";
        String password = "boblovesnoodles";
        URI uri = new URI(baseUrl + port + "/employees/" + email);
        employees.save(new Employee(email, password).hashingPassword());

        ResponseEntity<Employee> result = restTemplate.getForEntity(uri, Employee.class);
        Employee returned = result.getBody();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(email, returned.getEmail());
    }

    @Test
    public void getEmployeesByFirmEmail_Success() throws Exception {
        String firmEmail = "saladbar@gmail.com";
        String firmPassword = "saladbarney";
        Firm newFirm = new Firm(firmEmail, firmPassword);
        firms.save(newFirm);

        String email = "bob@gmail.com";
        String password = "boblovesnoodles";
        Employee newEmp = new Employee(email, password);
        newEmp.setFirm(newFirm);
        employees.save(newEmp.hashingPassword());

        URI uri = new URI(baseUrl + port + "/employees/firm/" + firmEmail);

        ResponseEntity<Employee[]> result = restTemplate.getForEntity(uri, Employee[].class);
        Employee[] returned = result.getBody();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, returned.length);
    }

    // @Test
    // public void addEmployee_NewEmail_Success() throws Exception {
    //     String firmEmail = "saladbar@gmail.com";
    //     String firmPassword = "saladbarney";
    //     Firm newFirm = new Firm(firmEmail, firmPassword);
    //     firms.save(newFirm);

    //     String email = "bob@gmail.com";
    //     String password = "boblovesnoodles";
    //     Employee newEmp = new Employee(email, password);

    //     URI uri = new URI(baseUrl + port + "/employees/firm/" + firmEmail);

    //     ResponseEntity<AuthenticationResponse> result = restTemplate.postForEntity(uri, newEmp, AuthenticationResponse.class);
    //     AuthenticationResponse returned = result.getBody();

    //     assertEquals(200, result.getStatusCodeValue());
    //     assertEquals(email, returned.getUser().getUsername());
    // }

    @Test
    public void addEmployee_ExistingEmail_Conflict() throws Exception {
        String firmEmail = "saladbar@gmail.com";
        String firmPassword = "saladbarney";
        Firm newFirm = new Firm(firmEmail, firmPassword);
        firms.save(newFirm);

        String email = "bob@gmail.com";
        String password = "boblovesnoodles";
        Employee newEmp = new Employee(email, password);
        newEmp.setFirm(newFirm);
        employees.save(newEmp.hashingPassword());

        URI uri = new URI(baseUrl + port + "/employees/firm/" + firmEmail);

        ResponseEntity<AuthenticationResponse> result = restTemplate.postForEntity(uri, newEmp, AuthenticationResponse.class);

        assertEquals(409, result.getStatusCodeValue());
    }

    // @Test
    // public void updateEmployee_ValidEmail_Success() throws Exception {
    //     String email = "bob@gmail.com";
    //     String password = "boblovesnoodles";
    //     Employee emp = new Employee(email, password);
    //     employees.save(emp.hashingPassword());

    //     Employee newEmp = new Employee(email, "boblovescake");

    //     URI uri = new URI(baseUrl + port + "/employees");

    //     ResponseEntity<Employee> result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newEmp),
    //             Employee.class);

    //     assertEquals(200, result.getStatusCodeValue());
    //     assertEquals(newEmp.getPassword(), result.getBody().getPassword());
    // }

    @Test
    public void updateEmployee_InvalidEmail_NotFound() throws Exception {
        String email = "bob@gmail.com";
        String password = "boblovesnoodles";
        Employee emp = new Employee(email, password);
        employees.save(emp.hashingPassword());

        Employee newEmp = new Employee("bobble@gmail.com", "boblovescake");

        URI uri = new URI(baseUrl + port + "/employees");

        ResponseEntity<Employee> result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newEmp),
                Employee.class);

        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    public void deleteEmployee_ValidEmail_Success() throws Exception {
        String email = "bob@gmail.com";
        String password = "boblovesnoodles";
        Employee emp = new Employee(email, password);
        employees.save(emp.hashingPassword());

        URI uri = new URI(baseUrl + port + "/employees/" + email);

        ResponseEntity<Void> result = restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);

        assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    public void deleteEmployee_InvalidEmail_NotFound() throws Exception {
        String email = "bob@gmail.com";

        URI uri = new URI(baseUrl + port + "/employees/" + email);

        ResponseEntity<Void> result = restTemplate.exchange(uri, HttpMethod.DELETE, null, Void.class);

        assertEquals(404, result.getStatusCodeValue());
    }
}

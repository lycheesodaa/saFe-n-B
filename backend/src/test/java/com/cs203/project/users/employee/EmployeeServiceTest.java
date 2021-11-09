package com.cs203.project.users.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cs203.project.users.firm.Firm;
import com.cs203.project.users.firm.FirmRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    
    @Mock
    private EmployeeRepository employees;

    @Mock
    private TemperatureRepository temperatures;

    @Mock
	private ARTRepository arts;

    @Mock
    private FirmRepository firms;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void getAllEmployees() {
        List<Employee> eList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            eList.add(new Employee());
        }
        when(employees.findAll()).thenReturn(eList);

        ResponseEntity<List<Employee>> result = employeeService.getAllEmployees();

        assertEquals(result.getBody().size(), 20);
        verify(employees).findAll();
    }

    @Test
    void getEmployee_ReturnEmployee() {
        String email = "bob@gmail.com";
        Employee emp = new Employee(email, "boblovesnoodles");
        when(employees.findByEmail(email)).thenReturn(emp);

        ResponseEntity<Employee> result = employeeService.getEmployeeByEmail(email);

        assertNotNull(result.getBody());
        verify(employees).findByEmail(email);
    }

    @Test
    void getEmployee_Return404() {
        String email = "bob@gmail.com";
        when(employees.findByEmail(email)).thenReturn(null);

        ResponseEntity<Employee> result = employeeService.getEmployeeByEmail(email);

        assertNull(result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        verify(employees).findByEmail(email);
    }

    @Test
    void addEmployee_ReturnEmployee() {
        String email = "bob@gmail.com";
        Employee emp = new Employee(email, "boblovesnoodles");
        String firmEmail = "saladbar@gmail.com";
        Firm firm = new Firm(email, "saladbarney");
        when(employees.findByEmail(anyString())).thenReturn(null);
        when(firms.findByEmail(anyString())).thenReturn(firm);
        when(employees.save(any(Employee.class))).thenReturn(emp);

        Employee result = employeeService.addEmployee(emp, firmEmail);

        assertNotNull(result);
        verify(employees).findByEmail(email);
        verify(firms).findByEmail(firmEmail);
        verify(employees).save(emp);
    }

    @Test
    void addEmployee_Exists_ReturnNull() {
        String email = "bob@gmail.com";
        Employee emp = new Employee(email, "boblovesnoodles");
        String firmEmail = "saladbar@gmail.com";
        when(employees.findByEmail(anyString())).thenReturn(emp);

        Employee result = employeeService.addEmployee(emp, firmEmail);

        assertNull(result);
        verify(employees).findByEmail(email);
    }

    @Test
    void addEmployee_NoFirmExists_ReturnNull() {
        String email = "bob@gmail.com";
        Employee emp = new Employee(email, "boblovesnoodles");
        String firmEmail = "saladbar@gmail.com";
        when(employees.findByEmail(anyString())).thenReturn(null);
        when(firms.findByEmail(anyString())).thenReturn(null);

        Employee result = employeeService.addEmployee(emp, firmEmail);

        assertNull(result);
        verify(employees).findByEmail(email);
        verify(firms).findByEmail(firmEmail);
    }

    @Test
    void updateEmployee_ReturnEmployee() {
        String email = "bob@gmail.com";
        Employee emp = new Employee(email, "boblovesnoodles");
        Employee updated = new Employee(email, "boblovesbread");

        when(employees.findByEmail(anyString())).thenReturn(emp);
        when(employees.save(any(Employee.class))).thenReturn(updated);

        ResponseEntity<Employee> result = employeeService.updateEmployee(updated);

        assertNotNull(result.getBody());
        verify(employees).findByEmail(email);
        verify(employees).save(updated);
    }

    @Test
    void updateEmployee_NotFound_Return404() {
        String email = "bob@gmail.com";
        Employee updated = new Employee(email, "boblovesbread");
        when(employees.findByEmail(anyString())).thenReturn(null);

        ResponseEntity<Employee> result = employeeService.updateEmployee(updated);

        assertNull(result.getBody());
        verify(employees).findByEmail(email);
    }

    @Test
    void deleteEmployee_NotFound_Return404() {
        String email = "bob@gmail.com";
        when(employees.findByEmail(anyString())).thenReturn(null);

        ResponseEntity<Void> result = employeeService.deleteEmployee(email);

        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);;
        verify(employees).findByEmail(email);
    }

    @Test
    void addTemp_ReturnEmployee() {
        Temperature newTemp = new Temperature(39.8);
        String email = "bob@gmail.com";
        Employee emp = new Employee(email, "boblovesnoodles");
        when(employees.findByEmail(anyString())).thenReturn(emp);
        when(temperatures.save(any(Temperature.class))).thenReturn(newTemp);

        ResponseEntity<Employee> result = employeeService.addTemperature(newTemp, email);

        assertNotNull(result.getBody());
        verify(employees, times(2)).findByEmail(email);
        verify(temperatures).save(newTemp);
    }

    @Test
    void addART_ReturnEmployee() {
        ART newART = new ART(true);
        String email = "bob@gmail.com";
        Employee emp = new Employee(email, "boblovesnoodles");
        when(employees.findByEmail(anyString())).thenReturn(emp);
        when(arts.save(any(ART.class))).thenReturn(newART);

        ResponseEntity<Employee> result = employeeService.addART(newART, email);

        assertNotNull(result.getBody());
        verify(employees, times(2)).findByEmail(email);
        verify(arts).save(newART);
    }
}

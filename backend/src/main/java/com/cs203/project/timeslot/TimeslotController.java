package com.cs203.project.timeslot;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.cs203.project.users.employee.EmployeeNotFoundException;
import com.cs203.project.users.employee.EmployeeRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class TimeslotController {
    public EmployeeRepository employees;
    public TimeslotService timeslotService;

    public TimeslotController(EmployeeRepository employees, TimeslotService timeslotService) {
        this.employees = employees;
        this.timeslotService = timeslotService;
    }

    /**
     * Getting all Timeslots 
     * @return - a list of all Timeslots
     */
    @GetMapping("/timeslots")
    public List<Timeslot> getTimeslots() {
        return timeslotService.listAll();
    }

    @GetMapping("/timeslots/{employeeId}")
    public List<Timeslot> getEmployeeTimeslots(@PathVariable(value = "employeeId") String email) {
        if (!employees.existsById(email)) {
            throw new EmployeeNotFoundException();
        }
        return timeslotService.getByEmployeeId(email);
    }

    @GetMapping("/timeslots/{date}")
    public Timeslot getTimeslotsByDate(@PathVariable LocalDate date) {
        Timeslot result = timeslotService.getByDate(date);
        if (result == null) {
            throw new TimeslotNotFoundException();
        }
        return result;
    }

    @GetMapping("/timeslots/{employeeId}/{date}")
    public Timeslot getEmployeeTimeslotByDate(@PathVariable(value = "employeeId") String email,
            @PathVariable(value = "date") LocalDate date) {
        if (!employees.existsById(email)) {
            throw new EmployeeNotFoundException();
        }
        Timeslot result = timeslotService.getByEmployeeIdAndDate(email, date);
        if (result == null) {
            throw new TimeslotNotFoundException();
        }
        return result;
    }



    @PostMapping("/timeslots/{employeeId}")
    public Timeslot addAvailability(@PathVariable(value = "employeeId") String email,
            @Valid @RequestBody Timeslot availability) {
        // TODO
        return null;
    }

    @PutMapping("/timeslots/{id}")
    public Timeslot updateAvailability(@PathVariable(value = "id") Long id,
            @Valid @RequestBody Timeslot availability) {
        // TODO
        return null;
    }

    @DeleteMapping("/timeslots/{id}")
    public ResponseEntity<?> deleteAvailability(@PathVariable(value = "employeeId") String email,
            @PathVariable(value = "id") Long id) {
        // TODO
        return null;
    }
}

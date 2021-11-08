package com.cs203.project.timeslot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cs203.project.users.employee.Employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TimeslotServiceTest {

    @Mock
    private TimeslotRepository timeslots;

    @InjectMocks
    TimeslotServiceImpl timeslotService;

    @Test
    void getAllTimeslots() {
        List<Timeslot> tsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tsList.add(new Timeslot());
        }
        when(timeslots.findAll()).thenReturn(tsList);

        List<Timeslot> result = timeslotService.listAll();

        assertEquals(result.size(), 5);
        verify(timeslots).findAll();
    }

    @Test
    void getTimeslotByEmpId_ReturnTimeslot() {
        LocalTime startTime = LocalTime.now();
        Timeslot ts = new Timeslot(LocalDate.now(), startTime, startTime.plusHours(5),
                Duration.between(startTime, startTime.plusHours(5)));
        String email = "bob@gmail.com";
        Employee emp = new Employee("bob@gmail.com", "xxx");
        ts.setEmployee(emp);
        List<Timeslot> tsList = new ArrayList<>();
        tsList.add(ts);

        when(timeslots.findByEmployeeEmail(anyString())).thenReturn(tsList);

        List<Timeslot> result = timeslotService.getByEmployeeId(email);

        assertEquals(result.size(), 1);
        verify(timeslots).findByEmployeeEmail(email);
    }

    @Test
    void addTimeslot_ReturnTimeslot() {
        LocalDate today = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        Timeslot ts = new Timeslot(today, startTime, startTime.plusHours(5),
                Duration.between(startTime, startTime.plusHours(5)));
        Employee emp = new Employee("bob@gmail.com", "xxx");
        ts.setEmployee(emp);

        when(timeslots.findByDate(any(LocalDate.class))).thenReturn(Optional.empty());
        when(timeslots.save(any(Timeslot.class))).thenReturn(ts);

        Timeslot result = timeslotService.addTimeslot(ts);

        assertNotNull(result);
        verify(timeslots).findByDate(today);
        verify(timeslots).save(ts);
    }

    @Test
    void addTimeslot_SameDate_ReturnNull() {
        LocalDate today = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        Timeslot ts = new Timeslot(today, startTime, startTime.plusHours(5),
                Duration.between(startTime, startTime.plusHours(5)));
        Timeslot tsSameDate = new Timeslot(today, startTime, startTime.plusHours(1),
                Duration.between(startTime, startTime.plusHours(1)));
        Employee emp = new Employee("bob@gmail.com", "xxx");
        ts.setEmployee(emp);

        when(timeslots.findByDate(any(LocalDate.class))).thenReturn(Optional.of(ts));

        Timeslot result = timeslotService.addTimeslot(tsSameDate);

        assertNull(result);
        verify(timeslots).findByDate(today);
    }
}

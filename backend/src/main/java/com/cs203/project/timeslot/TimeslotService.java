package com.cs203.project.timeslot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TimeslotService {
    List<Timeslot> listAll();

    List<Timeslot> getByEmployeeId(String email);

    // not as relevant as getByEmployeeIdAndDate()
    Timeslot getByIdAndEmployeeId(Long id, String email);

    // gets for all employees by date
    Timeslot getByDate(LocalDate date);

    Timeslot getByEmployeeIdAndDate(String email, LocalDate date);

    Timeslot addTimeslot(Timeslot timeslot);

    Timeslot updateTimeslot(Long id, Timeslot timeslot);

    void deleteTimeslot(Long id);

    void autoDelete();

    List<Timeslot> getTimeslots_ForWeek(LocalDate startDate, LocalDate endDate);

    List<Timeslot> getSpecificDateTime(LocalDate date, LocalTime startTime, LocalTime endTime);

}

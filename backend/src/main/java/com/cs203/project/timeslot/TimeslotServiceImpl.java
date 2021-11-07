package com.cs203.project.timeslot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TimeslotServiceImpl implements TimeslotService {
    private TimeslotRepository timeslots;

    public TimeslotServiceImpl(TimeslotRepository timeslots) {
        this.timeslots = timeslots;
    }

    @Override
    public List<Timeslot> listAll() {
        return timeslots.findAll();
    }

    @Override
    public List<Timeslot> getByEmployeeId(String email) {
        return timeslots.findByEmployeeId(email);
    }

    @Override
    public Timeslot getByIdAndEmployeeId(Long id, String email) {
        return timeslots.findByIdAndEmployeeId(id, email).orElse(null);
    }


    @Override
    public Timeslot getByDate(LocalDate date) {
        return timeslots.findByDate(date).orElse(null);
    }

    @Override
    public Timeslot getByEmployeeIdAndDate(String email, LocalDate date) {
        return timeslots.findByEmployeeIdAndDate(email, date).orElse(null);
    }

    @Override
    public Timeslot addTimeslot(Timeslot newTimeslot) {
        Timeslot existing = timeslots.findByDate(newTimeslot.getDate()).orElse(null);
        if (existing == null) {
            return timeslots.save(newTimeslot);
        } else {
            return null;
        }
    }

    @Override
    public Timeslot updateTimeslot(Long id, Timeslot newTimeslot) {
        return timeslots.findById(id).map(a -> {
            a.setDate(newTimeslot.getDate());
            a.setStartTime(newTimeslot.getStartTime());
            a.setEndTime(newTimeslot.getEndTime());
            return timeslots.save(a);
        }).orElse(null);
    }

    @Override
    public void deleteTimeslot(Long id) {
        timeslots.deleteById(id);
    }

    @Override
    @Scheduled(cron = "weekly")
    public void autoDelete() {
        // TODO
    }

    @Override
    public List<Timeslot> getTimeslots_ForWeek(LocalDate startDate, LocalDate endDate) {
        return timeslots.findBetween(startDate, endDate);
    }

    @Override
    public List<Timeslot> getSpecificDateTime(LocalDate date, LocalTime startTime, LocalTime endTime) {
        // TODO Auto-generated method stub
        return null;
    }
}

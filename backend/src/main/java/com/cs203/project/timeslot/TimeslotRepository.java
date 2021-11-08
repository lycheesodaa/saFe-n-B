package com.cs203.project.timeslot;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
    List<Timeslot> findByEmployeeEmail(String email);

    Optional<Timeslot> findByIdAndEmployeeEmail(Long id, String email);

    Optional<Timeslot> findByDate(LocalDate date);

    Optional<Timeslot> findByEmployeeEmailAndDate(String email, LocalDate date);

    // inclusive of first and last date
    @Query(value = "SELECT * FROM Timeslot ts WHERE (ts.date >= :from and ts.date <= :to)", nativeQuery = true)
    public List<Timeslot> findBetween(@Param("from") @DateTimeFormat(iso = ISO.DATE) LocalDate start,
            @Param("to") @DateTimeFormat(iso = ISO.DATE) LocalDate end);
}

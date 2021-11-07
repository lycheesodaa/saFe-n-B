package com.cs203.project.timeslot;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.cs203.project.users.employee.Employee;

import org.springframework.stereotype.Component;

import lombok.*;

/**
 * This class creates the available timeslots for each employee, mapped to a
 * specific day in the {@code dailyShift} class
 */
@Component
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}

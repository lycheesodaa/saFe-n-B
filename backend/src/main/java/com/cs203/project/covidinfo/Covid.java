package com.cs203.project.covidinfo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.*;

@Component
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Covid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Date should not be null")
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    // number of cases
    @PositiveOrZero
    @NotNull
    private long imported;

    @PositiveOrZero
    private long community;

    @PositiveOrZero
    private long dormitory;

    @PositiveOrZero
    private long newCases;

    // states (can be null)
    // these are cumulative
    @PositiveOrZero
    private long hospitalised;

    @PositiveOrZero
    private long critical;

    @PositiveOrZero
    private long deaths;

    @PositiveOrZero
    private long recovered;
}

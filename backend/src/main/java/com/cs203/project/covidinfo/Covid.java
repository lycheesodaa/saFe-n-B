package com.cs203.project.covidinfo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

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
    @NotNull(message = "Date should not be null")
    @PastOrPresent
    private Date id;

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

package com.cs203.project.covidinfo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Column(name = "covid_id")
    private Long id;

//    @NotNull(message = "Date should not be null")
//    @PastOrPresent
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate date;

    private Date date;
    
    
    @PositiveOrZero
    @NotNull
    private long totalCases;
    
    @PositiveOrZero
    @NotNull
    private long activeCases;
    
    @PositiveOrZero
    @NotNull
    private long deceased;
    
    @PositiveOrZero
    @NotNull
    private long discharged;
    
    @PositiveOrZero
    @NotNull
    private long critical;
    
    @PositiveOrZero
    @NotNull
    private long maleCases;
    
    @PositiveOrZero
    @NotNull
    private long femaleCases;
    
    @PositiveOrZero
    @NotNull
    private long genderUnidentifiedCases;
    
    @PositiveOrZero
    @NotNull
    private long localTransmissions;
    
    @PositiveOrZero
    @NotNull
    private long importedCases;
    
    @PositiveOrZero
    @NotNull
    private long importedOrLocalUnreportedCases;
    
    @PositiveOrZero
    @NotNull
    private long averageAge;
    
    @OneToMany(mappedBy="covid", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Nationality> nationalityList;
    
    @OneToMany(mappedBy="covid", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<InfectionSource> infectionSourceList;
    
    @OneToMany(mappedBy="covid", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Cluster> clusterList;
    
    
//    // number of cases
//    @PositiveOrZero
//    @NotNull
//    private long imported;
//
//    @PositiveOrZero
//    private long community;
//
//    @PositiveOrZero
//    private long dormitory;
//
//    @PositiveOrZero
//    private long newCases;
//
//    // states (can be null)
//    // these are cumulative
//    @PositiveOrZero
//    private long hospitalised;
//
//    @PositiveOrZero
//    private long critical;
//
//    @PositiveOrZero
//    private long deaths;
//
//    @PositiveOrZero
//    private long recovered;
}

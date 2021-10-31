package com.cs203.project.covidinfo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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


    @Column(nullable = false, updatable = false)  
    @CreationTimestamp
    private Date created_at;
    
    @NotNull
    @Type(type="text")
    private String regulations;
    
    
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
    @JsonManagedReference
    private List<Nationality> nationalityList;
    
    @OneToMany(mappedBy="covid", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<InfectionSource> infectionSourceList;
    
    @OneToMany(mappedBy="covid", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cluster> clusterList;
        
}

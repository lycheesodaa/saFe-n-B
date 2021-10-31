package com.cs203.project.users.employee;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ART {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "art_id")
    private long id;

    private LocalDate localDate;

    private boolean result;
}
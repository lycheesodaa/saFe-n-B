package com.cs203.project.users.employee;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

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
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")  
    private Date dateTaken;

    private boolean result;

    @ManyToOne
	@JoinColumn(name="employee_id")
	@JsonBackReference
	private Employee employee;
}

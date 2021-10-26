package com.cs203.project.covidinfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidRepository extends JpaRepository <Covid, Long> {
    // additional derived queries specified here will be implemented by Spring Data JPA
    // start the derived query with "findBy", then reference the entity attributes you want to filter

}

//Optional<Covid> findByDate(LocalDate date);
//List<Covid> findByDateBetween(LocalDate startDate, LocalDate endDate);

//@Query("SELECT SUM(newCases) FROM Covid c WHERE c.id < ?1")
//long getTotalCases(LocalDate date);

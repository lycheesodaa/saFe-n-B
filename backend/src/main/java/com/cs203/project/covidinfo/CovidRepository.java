package com.cs203.project.covidinfo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidRepository extends JpaRepository <Covid, Date> {
    // additional derived queries specified here will be implemented by Spring Data JPA
    // start the derived query with "findBy", then reference the entity attributes you want to filter
    List<Covid> findByIdBetween(Date startDate, Date endDate);
    @Query("SELECT SUM(newCases) FROM Covid c WHERE c.id < ?1")
    long getTotalCases(Date date);
}

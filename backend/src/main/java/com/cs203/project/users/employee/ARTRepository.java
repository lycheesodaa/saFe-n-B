package com.cs203.project.users.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ARTRepository extends JpaRepository<ART, Long> {
	
}


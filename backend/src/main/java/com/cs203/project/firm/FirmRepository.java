package com.cs203.project.firm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmRepository extends JpaRepository<Firm, String> {
	public Firm findByEmail(String email);
}

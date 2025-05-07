package com.example.customers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("SELECT c FROM Customer c  WHERE SUBSTRING(c.ssn, LENGTH(c.ssn) - 3, 4) = :ssn")
	List<Customer> findByLastFourDigitsOfSsnAndDateOfBirth(@Param("ssn") String ssn,
			@Param("dateOfBirth") String dateOfBirth);
}

package com.example.customers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@Disabled
@DataJpaTest
//@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TestEntityManager entityManager;
	
	@BeforeEach
	void setUp() {
		Customer testCustomer = getNewCustomer();
		customerRepository.save(testCustomer);
	}

	public Customer getNewCustomer() {
		Customer testCustomer = new Customer();
		testCustomer.setFullName("testuser");
		testCustomer.setSsn("667-12-1234");
		testCustomer.setDateOfBirth(new Date().toString());
		return testCustomer;
	}

	@Disabled
	@Test
	void savedCustomer_thenCanBeFoundByIdTest() {
		Customer testCustomer = getNewCustomer();
		
		Customer customer = customerRepository.findById(testCustomer.getId()).orElse(null);
		assertNotNull(customer);
		assertEquals(testCustomer.getFullName(), customer.getFullName());
		assertEquals(testCustomer.getSsn(), customer.getSsn());
	}
	
	@Disabled
	@Test
	void findByLastFourDigitsOfSsnAndDateOfBirthTest() {
		Customer testCustomer = getNewCustomer();
		
		List<Customer> customers = customerRepository.findByLastFourDigitsOfSsnAndDateOfBirth("1234", new Date().toString());
		Customer customer = customers.get(0);
		assertNotNull(customer);
		assertEquals(testCustomer.getFullName(), customer.getFullName());
		assertEquals(testCustomer.getSsn(), customer.getSsn());
	}
	
	@Test
	void deleteByIdTest() {
		Customer testCustomer = getNewCustomer();
		customerRepository.save(testCustomer);
		
		List<Customer> customers = customerRepository.findByLastFourDigitsOfSsnAndDateOfBirth("1234", new Date().toString());
		Customer customer = customers.get(0);
		assertNotNull(customer);
		assertEquals(testCustomer.getFullName(), customer.getFullName());
		assertEquals(testCustomer.getSsn(), customer.getSsn());
		
		customerRepository.deleteById(customer.getId());
		customers = customerRepository.findByLastFourDigitsOfSsnAndDateOfBirth("1234", new Date().toString());
		assertThat(customers == null);
		
	}
	
}

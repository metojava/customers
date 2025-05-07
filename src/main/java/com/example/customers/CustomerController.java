package com.example.customers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customers")
class CustomerController {

	private final CustomerRepository repository;

	CustomerController(CustomerRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/Customers")
	List<Customer> all() {
		return repository.findAll();
	}

	@GetMapping("/findCustomers")
	List<Customer> findCustomers(@RequestParam String ssn, @RequestParam String dateOfBirth) {
		List<Customer> res  = repository.findByLastFourDigitsOfSsnAndDateOfBirth(ssn, dateOfBirth);
		return res;
	}

	@PostMapping("/Customers")
	ResponseEntity<Customer> newCustomer(@RequestBody Customer newCustomer) {
		Customer customer = repository.save(newCustomer);
		return new ResponseEntity(customer, HttpStatus.CREATED);
	}

	@DeleteMapping("/Customers/{id}")
	void deleteCustomer(@PathVariable Long id) {
		Optional<Customer> customer = repository.findById(id);
		customer.ifPresent(c ->{
			repository.delete(c);
		});

	}

}

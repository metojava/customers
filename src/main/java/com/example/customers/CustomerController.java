package com.example.customers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = new Date();
//		try {
//			date = formatter.parse(dateOfBirth);
//			System.out.println("Date object: " + date);
//		} catch (ParseException e) {
//			System.out.println("Error parsing date: " + e.getMessage());
//		}
		List<Customer> res  = repository.findByLastFourDigitsOfSsnAndDateOfBirth(ssn, dateOfBirth);
		//res.forEach(System.out::println);
		return res; //repository.findBySsnAndDateOfBirth(ssn, date);
		//return repository.findCustomers(ssn, date);
	}

	@PostMapping("/Customers")
	ResponseEntity<Customer> newCustomer(@RequestBody Customer newCustomer) {
		Customer customer = repository.save(newCustomer);
		return new ResponseEntity(customer, HttpStatus.CREATED);
	}

}

package com.example.customers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
public final class CustomerControllerTest {

//	@LocalServerPort
//	private int port;
	
//	@Autowired
//	private TestRestTemplate testRestTemplate;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private CustomerRepository customerRepository;
	
	
	@Autowired
	CustomerController customerController;

	@Disabled
	@Test
	void contextLoads() throws Exception {
		assertThat(customerController).isNotNull();
	}

	public Customer getNewCustomer() {
		Customer testCustomer = new Customer();
		testCustomer.setId(1l);
		testCustomer.setFullName("testuser");
		testCustomer.setSsn("667-12-1234");
		testCustomer.setDateOfBirth(new Date().toString());
		return testCustomer;
	}
	
	@Test
    public void testNewCustomer() throws Exception {
        // Arrange
		Customer testCustomer = getNewCustomer();
        String requestBody = objectMapper.writeValueAsString(testCustomer);
        customerRepository.delete(testCustomer);

        when(customerRepository.save(testCustomer)).thenReturn(testCustomer);

        mockMvc.perform(post("/customers/Customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName").value("testuser"));
    }

	//@Disabled
	@Test
	void getCustomers() throws Exception {

		List<Customer> expectedList = new ArrayList<>();
		Customer customer1 = getNewCustomer();
		customer1.setId(1l);
		Customer customer2 = getNewCustomer();
		customer2.setId(2l);
		customer2.setFullName("anothertestuser");
		expectedList.add(customer2);
		expectedList.add(customer1);
		int expectedSize = expectedList.size();

		when(customerRepository.findAll()).thenReturn(expectedList);

		ResultActions result = mockMvc.perform(get("/customers/Customers"));
		result.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$",hasSize(expectedSize)))
		.andExpect(jsonPath("$.[1].['id']").value(1))
		.andExpect(jsonPath("[0].id", is(2)))
		.andExpect(jsonPath("[0].['fullName']", is("anothertestuser")))
		.andExpect(jsonPath("[0].ssn", is("667-12-1234")));


		verify(customerRepository, times(1)).findAll();
	}
	
	//@Disabled
	@Test
	void findCustomerBySssnAndBirthDateTest() throws Exception{
		List<Customer> expectedList = new ArrayList<>();
		Customer customer1 = getNewCustomer();
		//Customer customer2 = getNewCustomer();
		//customer2.setFullName("anothertestuser");
		//expectedList.add(customer2);
		expectedList.add(customer1);
		int expectedSize = expectedList.size();

		String dateString = new Date().toString();
		when(customerRepository.findByLastFourDigitsOfSsnAndDateOfBirth("1234", dateString)).thenReturn(expectedList);
	
		mockMvc.perform(get("/customers/findCustomers")
				.param("ssn", "1234")
				.param("dateOfBirth", dateString)).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(expectedSize)))
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].fullName", is("testuser")));
		//.andExpect(jsonPath("$[1].id", is(2)))
		//.andExpect(jsonPath("$[1].name", is("anothertestuser")));
		
	}

	//@Disabled
//	@Test
//	void getListofCustomersa() throws Exception {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//		ResponseEntity<List<Customer>> response = this.testRestTemplate.exchange(
//				"http://localhost:" + port + "/customers/Customers", HttpMethod.GET, entity,
//				new ParameterizedTypeReference<List<Customer>>() {
//				});
//		List<Customer> customers = response.getBody();
//		assertThat(customers.size() != 0);
//	}
	
}

package com.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Customer;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment=WebEnvironment.RANDOM_PORT)
public class RestApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		System.out.println("context loaded");
	}

	@LocalServerPort
	int randomServerPort;

	@Test
	public void testAddCustomerSuccess() throws URISyntaxException, ParseException {
		System.out.println("port number : "+randomServerPort);
		final String baseUrl = "http://localhost:" + randomServerPort + "/add";
		URI uri = new URI(baseUrl);
		Customer customer = new Customer();
		customer.setName("abcd");
		customer.setEmail("abc@yahoo.com");
		customer.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-02-17"));
		HttpHeaders headers = new HttpHeaders();
		// headers.set("X-COM-PERSIST", "true");
		// headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Customer> request = new HttpEntity<>(customer, headers);

		ResponseEntity<Customer> result = this.restTemplate.postForEntity(uri, request, Customer.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void testListCustomers() throws URISyntaxException {
		System.out.println("port number : "+randomServerPort);
		final String baseUrl = "http://localhost:" + randomServerPort + "/customers";
		URI uri = new URI(baseUrl);
		
		//HttpHeaders headers = new HttpHeaders();
		// headers.set("X-COM-PERSIST", "true");


		ResponseEntity<Customer[]> result = this.restTemplate.getForEntity(uri, Customer[].class);
		System.out.println("Body : "+result.getBody().length);
		for(Customer cust : result.getBody()){
			System.out.println(cust);
		}
		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
}

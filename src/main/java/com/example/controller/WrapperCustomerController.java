package com.example.controller;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.Customer;


@RestController 
@RequestMapping("/custwrapper")
public class WrapperCustomerController {
	protected Logger logger = Logger.getLogger(WrapperCustomerController.class.getName());

	@Autowired
	private RestTemplate restTemplate;
	private static final String SERVICE_URL= "http://localhost:8080";

	
	//http://localhost:8080/custwrapper/customers
	@RequestMapping("/customers")
	public Customer[] all() {
		logger.info("custwrapper-microservice all() invoked");
		Customer[] customers = restTemplate.getForObject(SERVICE_URL+"/customers", Customer[].class);
		logger.info("customers-microservice all() found: " + customers.length);
		return customers;
	}

    //http://localhost:8080/custwrapper/customers/2
	@RequestMapping(path="/customers/{id}",method=RequestMethod.GET)
	public Customer byId(@PathVariable("id") Long id) {
		logger.info("custwrapper-microservice byId() invoked: " + id);
		Customer customer = restTemplate.getForObject(SERVICE_URL+"/customers/"+id, Customer.class);
		logger.info("custwrapper-microservice byId() found: " + customer);
		return customer;
	}
	
    //http://localhost:8080/custwrapper/add  --post request check content type application/json
	//postman --
/*	 {
	        "name": "bbgg",
	        "email": "66@yahoo.com",
	        "date": 1487097000000
	    }*/
	@RequestMapping(path="/add",method=RequestMethod.POST
			//, consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public Customer add(@RequestBody Customer customer) {
		logger.info("custwrapper-microservice add() invoked: " + customer);
	    HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Customer> request = new HttpEntity<>(customer,headers);
	      
		Customer addedCustomer =restTemplate.postForObject(SERVICE_URL+"/add", request, Customer.class);
		System.out.println("custwrapper-microservice add() found: " + addedCustomer);
		return addedCustomer;
	}
	
    //http://localhost:8080/custwrapper/customers/delete/2
	@RequestMapping(path="/customers/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		logger.info("custwrapper-microservice delete() invoked: " + id);
	    restTemplate.getForObject(SERVICE_URL+"/customers/"+id, Void.class);
		logger.info("custwrapper-microservice delete() found: " + id);
		return "customer deleted : "+id;
	}
}

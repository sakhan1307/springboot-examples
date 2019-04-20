package com.example.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.service.CustomerService;

@RestController //("/cust")
//@RequestMapping("/cust")
public class CustomerController {
	protected Logger logger = Logger.getLogger(CustomerController.class.getName());

	@Autowired
	private CustomerService customerService;

	
	//http://localhost:8080/customers
	@RequestMapping("/customers")
	public Customer[] all() {
		logger.info("customers-microservice all() invoked");
		Customer[] customers = customerService.findAll();
		logger.info("customers-microservice all() found: " + customers.length);
		return customers;
	}

    //http://localhost:8080/customers/2
	@RequestMapping(path="/customers/{id}",method=RequestMethod.GET)
	public Customer byId(@PathVariable("id") Long id) {
		logger.info("customers-microservice byId() invoked: " + id);
		Customer customer = customerService.findById(id);
		logger.info("customers-microservice byId() found: " + customer);
		return customer;
	}
	
    //http://localhost:8080/add  --post request check content type application/json
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
		logger.info("customers-microservice add() invoked: " + customer);
		Customer addedCustomer = customerService.save(customer);
		logger.info("customers-microservice add() found: " + addedCustomer);
		return addedCustomer;
	}
	
    //http://localhost:8080/customers/delete/2
	@RequestMapping(path="/customers/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		logger.info("customers-microservice delete() invoked: " + id);
		 customerService.delete(id);
		logger.info("customers-microservice delete() found: " + id);
		return "customer deleted : "+id;
	}
}

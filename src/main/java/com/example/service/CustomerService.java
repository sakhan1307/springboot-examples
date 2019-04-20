package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.example.model.Customer;

public interface CustomerService {
	
	Customer[] findAll();
	
	Customer findOne(Long id);

	List<Customer> findByEmail(String email);

	Stream<Customer> findByEmailReturnStream(String email);

	List<Customer> findByDate(Date date);

	// Stream<Customer> findAllAndStream();

	// List<Customer> findByDateBetween(Date from, Date to);

	Customer findById(Long id);
	
	Customer save(Customer customer);
	
	void delete(Long id);
}

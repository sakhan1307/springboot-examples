package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerRepository;
import com.example.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	

	@Override
	public List<Customer> findByEmail(String email) {
		
		return customerRepository.findByEmail(email);
	}

	@Override
	public Stream<Customer> findByEmailReturnStream(String email) {
	
		return customerRepository.findByEmailReturnStream(email);
	}

	@Override
	public List<Customer> findByDate(Date date) {
		
		return customerRepository.findByDate(date);
	}

	@Override
	public Customer findById(Long id) {
		
		return customerRepository.findById(id);
	}

	@Override
	public Customer[] findAll() {
		List<Customer> custList = new ArrayList<>();
		// customerRepository.findAll().forEach(custList::add);
		customerRepository.findAll().forEach(e -> custList.add(e));
		 return custList.toArray(new Customer[custList.size()]);
	}

	@Override
	public Customer findOne(Long id) {
		
		return customerRepository.findOne(id);
	}

	@Override
	public Customer save(Customer customer) {
		
		return customerRepository.save(customer);
	}

	@Override
	public void delete(Long id) {
		customerRepository.delete(id);
	}

}

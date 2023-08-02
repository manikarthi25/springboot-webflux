package com.mani.webflux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.webflux.dao.CustomerDao;
import com.mani.webflux.dto.Customer;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public List<Customer> getCustomers() {
		long start = System.currentTimeMillis();
		List<Customer> customerList = customerDao.getCustomers();
		long end = System.currentTimeMillis();
		System.out.println("Total Time : "+(end -start));
		return customerList;
	}

	public Flux<Customer> getCustomersStream() {
		long start = System.currentTimeMillis();
		Flux<Customer> customerList = customerDao.getCustomersStream();
		long end = System.currentTimeMillis();
		System.out.println("Total Time : "+(end -start));
		return customerList;
	}

}

package com.mani.webflux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mani.webflux.dto.Customer;
import com.mani.webflux.service.CustomerService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	// Make difference between Reactive Programming and Non Reactive Programming

	@Autowired
	private CustomerService customerService;

	@GetMapping("/non-reactive")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@GetMapping(value = "/reactive", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getCustomersStream() {
		return customerService.getCustomersStream();
	}

}

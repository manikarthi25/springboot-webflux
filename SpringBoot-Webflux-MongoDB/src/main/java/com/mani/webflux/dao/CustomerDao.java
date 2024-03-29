package com.mani.webflux.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.mani.webflux.dto.Customer;
import reactor.core.publisher.Flux;

@Component
public class CustomerDao {
	
	public static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Customer> getCustomers() {
		return IntStream.rangeClosed(1, 10)
				.peek(CustomerDao::sleepExecution)
				.peek(i -> System.out.println("Processing customer : "+i))
				.mapToObj(i -> new Customer(i, "Customer-"+i))
				.collect(Collectors.toList());
		
	}

	public Flux<Customer> getCustomersStream() {
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("Processing customer using stream : "+i))
				.map(i -> new Customer(i, "Customer-"+i));
	}

	public Flux<Customer> getCustomersRouter() {
		return Flux.range(1, 10)
				.doOnNext(i -> System.out.println("Processing customer using stream : "+i))
				.map(i -> new Customer(i, "Customer-"+i));
	}

}

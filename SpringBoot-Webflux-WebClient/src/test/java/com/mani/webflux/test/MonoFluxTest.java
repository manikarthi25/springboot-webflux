package com.mani.webflux.test;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
	
	@Test
	public void testMonoComplete() {
		
		Mono<String> monoString = Mono.just("Mani").log(); // create Mono with single object - Publisher
		
		monoString.subscribe(System.out::println); // publisher.subscribe()
		
		/*
		 [ INFO] (main) | onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription)
		 [ INFO] (main) | request(unbounded)
		 [ INFO] (main) | onNext(Mani)
		 Mani
		 [ INFO] (main) | onComplete()
		*/
	}
	
	@Test
	public void testMonoError() {
		
		Mono<?> monoString = Mono.just("Mani")
				.then(Mono.error(new RuntimeException("Mono Runtime Exception")))
				.log(); // create Mono with single object - Publisher
		
		monoString.subscribe(System.out::println, e -> System.out.println(e.getMessage())); // publisher.subscribe()
		
	}
	
	@Test
	public void textFluxComplete() {
		
		Flux<String> fluxString = Flux.just("Mani","Mohith","Mahith")
				.concatWithValues("raj")
				.log(); // Create Flux with more than one object -> Publisher pushish many event like mani, mohith, mahith
		fluxString.subscribe(System.out::println); 
		
		/*
		 [ INFO] (main) | onSubscribe([Synchronous Fuseable] FluxArray.ArraySubscription)
		 [ INFO] (main) | request(unbounded)
		 [ INFO] (main) | onNext(Mani)
		 Mani
		 [ INFO] (main) | onNext(Mohith)
		 Mohith
		 [ INFO] (main) | onNext(Mahith)
		 Mahith
		 [ INFO] (main) | onComplete()
		 */
	}

	@Test
	public void textFluxError() {
		
		Flux<?> fluxString = Flux.just("Mani","Mohith","Mahith")
				.concatWith(Flux.error(new RuntimeException("Flux Exception")))
				.log(); // Create Flux with more than one object -> Publisher pushish many event like mani, mohith, mahith
		fluxString.subscribe(System.out::println); 
	}
}

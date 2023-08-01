package com.mani.webflux.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.mani.webflux.entity.ProductEntity;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {
	Flux<ProductEntity> findByPriceBetween(Range<Double> priceRange);
}

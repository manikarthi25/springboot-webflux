package com.mani.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.mani.webflux.entity.ProductEntity;


public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Integer> {

}

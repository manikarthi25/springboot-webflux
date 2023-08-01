package com.mani.webflux.service;

import com.mani.webflux.dto.ProductDto;
import com.mani.webflux.repository.ProductRepository;
import com.mani.webflux.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts() {
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> getProductById(Integer id) {
        return productRepository.findById(id)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> monoProductDto) {
        return monoProductDto.map(AppUtils::dtoToEntity)
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, Integer id) {
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id))).flatMap(productRepository::save)
                        .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProductById(Integer id) {
        return productRepository.deleteById(id);
    }
}

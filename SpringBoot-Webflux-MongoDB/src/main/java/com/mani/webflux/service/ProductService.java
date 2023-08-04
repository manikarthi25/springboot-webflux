package com.mani.webflux.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.mani.webflux.dto.ProductDto;
import com.mani.webflux.repository.ProductRepository;
import com.mani.webflux.utils.AppUtils;
import com.mani.webflux.utils.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts() {
        logger.info("ProductService | getProducts");
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        logger.info("ProductService | getProductById");
        return productRepository.findById(id)
                .map(AppUtils::entityToDto);
    }

    public Flux<ProductDto> getProductsInRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max))
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> monoProductDto) {
        logger.info("ProductService | getProductById | ProductDto : {}", Mapper.mapToJsonObject(monoProductDto));
        return monoProductDto.map(AppUtils::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id))).flatMap(productRepository::save)
                        .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProductById(String id) {
        return productRepository.deleteById(id);
    }
}

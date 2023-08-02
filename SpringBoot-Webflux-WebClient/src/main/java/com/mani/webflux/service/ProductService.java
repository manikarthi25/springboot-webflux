package com.mani.webflux.service;

import com.mani.webflux.dto.ProductDto;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder().baseUrl("http://localhost:8181/product").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    public Flux<ProductDto> getProducts() {
        return webClient.get().retrieve().bodyToFlux(ProductDto.class);
    }

    public Mono<ProductDto> getProductById(String id) {
        return webClient.get().uri("/"+id).retrieve().bodyToMono(ProductDto.class);
    }

    public Mono<ProductDto> saveProduct(ProductDto productDto) {
        return webClient.post().bodyValue(productDto).retrieve().bodyToMono(ProductDto.class);
    }

}

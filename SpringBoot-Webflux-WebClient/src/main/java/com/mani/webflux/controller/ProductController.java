package com.mani.webflux.controller;

import com.mani.webflux.dto.ProductDto;
import com.mani.webflux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProducts(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

}

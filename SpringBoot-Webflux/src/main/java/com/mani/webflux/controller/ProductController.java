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

    @GetMapping("/product-range")
    public Flux<ProductDto> getProductsInRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return productService.getProductsInRange(min, max);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return productService.saveProduct(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id) {
        return productService.updateProduct(productDtoMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteById(@PathVariable String id) {
        return productService.deleteProductById(id);
    }
}

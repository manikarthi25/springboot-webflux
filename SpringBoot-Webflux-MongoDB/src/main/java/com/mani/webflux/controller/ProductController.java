package com.mani.webflux.controller;

import com.mani.webflux.dto.ProductDto;
import com.mani.webflux.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@RestController
@RequestMapping("/product")
public class ProductController {

    Logger logger = LogManager.getLogger(ProductController.class);

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

    @GetMapping("/jobstatus")
    public String getJobStatus() {
        boolean boolValue =  new Random().nextBoolean();
        if(boolValue) {
            logger.info("ProductController | getJobStatus | JobStatus : {} ", "Success");
            return "Success";
        } else {
            logger.info("ProductController | getJobStatus | JobStatus : {} ", "Failed");
            return "Failed";
        }
    }

}

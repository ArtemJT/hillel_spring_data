package com.example.hw_25_spring_data.controller;

import com.example.hw_25_spring_data.dto.ProductDto;
import com.example.hw_25_spring_data.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/getAllProducts")
    public List<ProductDto> readAllProducts() {
        List<ProductDto> allProducts = productService.getAllProducts();
        allProducts.forEach(productDto -> log.info(productDto.toString()));
        return allProducts;
    }

    @GetMapping(value = "/getProduct/{id}")
    public ProductDto readProduct(@PathVariable Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument {id} is null");
        } else {
            ProductDto productDto = null;
            String logMsg;
            try {
                productDto = productService.getProductById(id);
                logMsg = productDto.toString();
            } catch (NoSuchElementException e) {
                logMsg = e.getMessage();
            }
            log.info("{}", logMsg);
            return productDto;
        }
    }

    @PutMapping(value = "/addProducts/{qty}")
    public void writeProducts(@PathVariable Integer qty) {
        if (qty == null || qty == 0) {
            throw new IllegalArgumentException("Argument {qty} is null");
        } else {
            productService.addProducts(qty);
        }
    }

}

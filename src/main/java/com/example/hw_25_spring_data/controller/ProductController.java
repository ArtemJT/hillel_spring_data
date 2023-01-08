package com.example.hw_25_spring_data.controller;

import com.example.hw_25_spring_data.dto.ProductDto;
import com.example.hw_25_spring_data.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import static com.example.hw_25_spring_data.controller.OrderController.EXC_MSG;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PutMapping(value = "/addProducts")
    public String writeProducts(@RequestParam Integer qty) {
        if (qty == null || qty == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be greater than zero");
        } else {
            return productService.addProducts(qty);
        }
    }

    @GetMapping(value = "/getAllProducts")
    public List<ProductDto> readAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/getProduct")
    public ProductDto readProduct(@RequestParam int id) {
        try {
            return productService.getProductById(id);
        } catch (NoSuchElementException e) {
            log.info("{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EXC_MSG);
        }
    }

    @DeleteMapping(value = "/delAllProducts")
    public String removeAllProducts() {
        return productService.removeAllProducts();
    }
}

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
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "{quantity}")
    public String writeProducts(@PathVariable Integer quantity) {
        if (quantity == null || quantity == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be greater than zero");
        } else {
            return productService.addProducts(quantity);
        }
    }

    @GetMapping
    public List<ProductDto> readAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "{id}")
    public ProductDto readProduct(@PathVariable int id) {
        try {
            return productService.getProductById(id);
        } catch (NoSuchElementException e) {
            log.error("{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, EXC_MSG);
        }
    }

    @DeleteMapping
    public String removeAllProducts() {
        return productService.removeAllProducts();
    }
}

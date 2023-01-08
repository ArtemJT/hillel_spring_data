package com.example.hw_25_spring_data.service;

import com.example.hw_25_spring_data.dto.ProductDto;
import com.example.hw_25_spring_data.entities.Product;
import com.example.hw_25_spring_data.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;
    private int index = 1;

    public void addProducts(int quantity) {
        for (int i = quantity; i > 0; i--) {
            String name = String.format("product-%d", index);
            double cost = Double.parseDouble(String.valueOf(index) + index + "." + index + index);

            ProductDto productDto = new ProductDto(null, name, cost);
            addProduct(productDto);
            index++;
        }
    }

    public List<ProductDto> getAllProducts() {
        return Streams.stream(productRepository.findAll())
                .map(this::convertProductToDto)
                .toList();
    }

    public ProductDto getProductById(int id) {
        return convertProductToDto(productRepository.findById(id).orElseThrow());
    }

    private ProductDto addProduct(ProductDto dto) {
        Product product = objectMapper.convertValue(dto, Product.class);
        productRepository.save(product);
        dto.setId(product.getId());
        log.info("Added new product: {}", dto);
        return dto;
    }

    private ProductDto convertProductToDto(Product product) {
        return objectMapper.convertValue(product, ProductDto.class);
    }
}

package com.example.hw_25_spring_data.model.service;

import com.example.hw_25_spring_data.dto.ProductDto;
import com.example.hw_25_spring_data.model.entities.Product;
import com.example.hw_25_spring_data.model.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {ProductService.class})
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepo productRepo;
    @MockBean
    private ObjectMapper objectMapper;

    private final ServiceTestsContext context = ServiceTestsContext.getInstance();
    private final List<ProductDto> productDtoList = context.getProductDtoList();
    private final List<Product> productList = context.getProductList();
    private final Product product = context.getProduct();
    private final ProductDto productDto = context.getProductDto();

    @Test
    void addProducts() {
        productDto.setName("product-1");
        productDto.setCost(11.11);

        assertEquals("Added new product: " + productDto, productService.addProducts(1));

        StringJoiner stringJoiner = new StringJoiner("\nAdded new product: ", "Added new product: ", "");
        productDtoList.forEach(productDto -> stringJoiner.add(productDto.toString()));

        assertEquals(stringJoiner.toString(), productService.addProducts(3));
    }

    @Test
    void getAllProducts() {
        Mockito.doReturn(productList)
                .when(productRepo)
                .findAll();
        Mockito.doReturn(productDto)
                .when(objectMapper)
                .convertValue(product, ProductDto.class);

        assertEquals(productDtoList, productService.getAllProducts());
    }

    @Test
    void getProductById() {
        product.setName(productDto.getName());
        product.setCost(productDto.getCost());

        Optional<Product> productOptional = Optional.of(product);
        Mockito.doReturn(productOptional)
                .when(productRepo)
                .findById(1);
        Mockito.doReturn(productDto)
                .when(objectMapper)
                .convertValue(product, ProductDto.class);

        assertThrows(NoSuchElementException.class, () -> productService.getProductById(0));
        assertEquals(productDto, productService.getProductById(1));
    }

//    private void addProductToList() {
//        index++;
//        product = new Product();
//        product.setName("product-" + index);
//        product.setCost(Double.parseDouble(String.valueOf(index) + index + "." + index + index));
//        productList.add(product);
//    }
}
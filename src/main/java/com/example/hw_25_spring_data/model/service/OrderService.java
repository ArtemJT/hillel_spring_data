package com.example.hw_25_spring_data.service;

import com.example.hw_25_spring_data.dto.OrderDto;
import com.example.hw_25_spring_data.dto.ProductDto;
import com.example.hw_25_spring_data.entities.Order;
import com.example.hw_25_spring_data.entities.Product;
import com.example.hw_25_spring_data.repository.OrderRepository;
import com.example.hw_25_spring_data.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public OrderDto addOrder(int... productId) {
        Set<Product> productList = new HashSet<>();
        Arrays.stream(productId).forEach(id -> productList.add(productRepository.findById(id).orElseThrow()));
//        Arrays.stream(productId).forEach(id -> productList.add(productService.getProductById(id)));

        double cost = productList.stream().mapToDouble(Product::getCost).sum();

        OrderDto dto = new OrderDto(productList, cost);
        Order order = objectMapper.convertValue(dto, Order.class);
//        Order order = new Order();
//        order.setDate(dto.getDate());
//        order.setCost(cost);
//        order.setProducts(productList);
        orderRepository.save(order);
        dto.setId(order.getId());
        return dto;
    }
}

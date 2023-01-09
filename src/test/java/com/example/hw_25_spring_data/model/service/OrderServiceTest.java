package com.example.hw_25_spring_data.model.service;

import com.example.hw_25_spring_data.dto.OrderDto;
import com.example.hw_25_spring_data.dto.ProductDto;
import com.example.hw_25_spring_data.model.entities.Order;
import com.example.hw_25_spring_data.model.entities.Product;
import com.example.hw_25_spring_data.model.repo.OrderRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {OrderService.class})
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepo orderRepo;
    @MockBean
    private ProductService productService;
    @MockBean
    private ObjectMapper objectMapper;

    private final ServiceTestsContext context = ServiceTestsContext.getInstance();
    private final Order order = context.getOrder();
    private final OrderDto orderDto = context.getOrderDto();
    private final List<Product> productList = context.getProductList();
    private final List<ProductDto> productDtoList = context.getProductDtoList();

//    @Test
    void addOrder() {
        assertEquals("Added new order: " + orderDto, orderService.addOrder(1));
    }

//    @Test
    void getAllOrders() {
        List<OrderDto> orderDtoList = List.of(orderDto);
        List<Order> orderList = List.of(order);

        Mockito.doReturn(orderList)
                .when(orderRepo)
                .findAll();

        Mockito.doReturn(productDtoList)
                .when(objectMapper)
                .convertValue(productList, ProductDto.class);

        assertEquals(orderDtoList, orderService.getAllOrders());
    }

    @Test
    void getOrderById() {
        Optional<Order> orderOptional = Optional.of(order);
        Mockito.doReturn(orderOptional)
                .when(orderRepo)
                .findById(1);
        Mockito.doReturn(orderDto)
                .when(objectMapper)
                .convertValue(order, OrderDto.class);

        assertThrows(NoSuchElementException.class, () -> orderService.getOrderById(0));
        assertEquals(orderDto, orderService.getOrderById(1));
    }
}
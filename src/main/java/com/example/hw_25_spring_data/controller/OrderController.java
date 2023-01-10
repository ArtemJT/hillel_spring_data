package com.example.hw_25_spring_data.controller;

import com.example.hw_25_spring_data.dto.OrderDto;
import com.example.hw_25_spring_data.model.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    protected static final String EXC_MSG = "ID NOT FOUND";

    @PutMapping(value = "/addOrder")
    public String writeOrder(@RequestParam int... id) {
        try {
            return orderService.addOrder(id);
        } catch (NoSuchElementException e) {
            log.error("{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EXC_MSG);
        }
    }

    @GetMapping(value = "/getOrder")
    public OrderDto readOrder(@RequestParam int id) {
        try {
            return orderService.getOrderById(id);
        } catch (NoSuchElementException e) {
            log.error("{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EXC_MSG);
        }
    }

    @GetMapping(value = "/getAllOrders")
    public List<OrderDto> readAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping(value = "/delOrder")
    public String removeOrder(@RequestParam int id) {
        try {
            return orderService.removeOrder(id);
        } catch (NoSuchElementException e) {
            log.error("{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EXC_MSG);
        }
    }

    @DeleteMapping(value = "/delOrderByProductId")
    public String removeOrderByProdId(@RequestParam int id) {
        try {
            return orderService.removeOrderByProdId(id);
        } catch (NoSuchElementException e) {
            log.error("{}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EXC_MSG);
        }
    }

    @DeleteMapping(value = "/delAllOrders")
    public String removeAllOrders() {
        return orderService.removeAllOrders();
    }
}

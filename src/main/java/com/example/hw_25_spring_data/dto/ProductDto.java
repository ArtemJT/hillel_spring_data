package com.example.hw_25_spring_data.dao;

import lombok.*;

@Data
public class ProductDto {

    private Integer id;
    private final String name;
    private final double cost;
}


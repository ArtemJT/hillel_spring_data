package com.example.hw_25_spring_data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer id;
    private String name;
    private double cost;
}


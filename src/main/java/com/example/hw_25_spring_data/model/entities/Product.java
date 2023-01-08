package com.example.hw_25_spring_data.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "store_db")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String name;

    private double cost;

    @ManyToOne
//    @JoinColumn(name = "id", nullable = false)
    private Order order;
}

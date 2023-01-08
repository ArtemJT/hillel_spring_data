package com.example.hw_25_spring_data.repository;

import com.example.hw_25_spring_data.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}

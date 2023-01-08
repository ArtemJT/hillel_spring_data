package com.example.hw_25_spring_data.model.repo;

import com.example.hw_25_spring_data.model.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
}

package com.example.hw_25_spring_data.model.repo;

import com.example.hw_25_spring_data.model.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends PagingAndSortingRepository<Order, Integer>, CrudRepository<Order, Integer> {
}

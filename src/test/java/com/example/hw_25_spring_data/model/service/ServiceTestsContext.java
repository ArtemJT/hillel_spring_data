package com.example.hw_25_spring_data.model.service;

import com.example.hw_25_spring_data.dto.OrderDto;
import com.example.hw_25_spring_data.dto.ProductDto;
import com.example.hw_25_spring_data.model.entities.Order;
import com.example.hw_25_spring_data.model.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ServiceTestsContext {

    private List<ProductDto> productDtoList;
    private List<Product> productList;
    private Product product;
    private ProductDto productDto;
    private Order order;
    private OrderDto orderDto;

    private ServiceTestsContext() {
        setUpContest();
    }

    public Order getOrder() {
        return order;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    private int index = 1;

    private static ServiceTestsContext serviceTestsContext;

    public static ServiceTestsContext getInstance() {
        if (serviceTestsContext == null) {
            serviceTestsContext = new ServiceTestsContext();
        }
        return serviceTestsContext;
    }

    public Product getProduct() {
        return product;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    private void setUpContest() {
        productDtoList = List.of(
                new ProductDto(null, "product-2", 22.22),
                new ProductDto(null, "product-3", 33.33),
                new ProductDto(null, "product-4", 44.44)
        );
        product = new Product();
        productDto = new ProductDto();

        productList = new ArrayList<>();

        addProductToList();
        addProductToList();
        addProductToList();

        double cost = productDtoList.stream().mapToDouble(ProductDto::getCost).sum();

        orderDto = new OrderDto();
        orderDto.setId(1);
        orderDto.setProducts(productDtoList);
        orderDto.setCost(cost);

        order = new Order();
        order.setId(1);
        order.setDate(orderDto.getDate());
        order.setProducts(productList);
        order.setCost(cost);
    }

    private void addProductToList() {
        ++index;
        Product prod = new Product();
        prod.setName("product-" + index);
        prod.setCost(Double.parseDouble(String.valueOf(index) + index + "." + index + index));
        productList.add(prod);
    }

    public ProductDto getProductDto() {
        return productDto;
    }
}

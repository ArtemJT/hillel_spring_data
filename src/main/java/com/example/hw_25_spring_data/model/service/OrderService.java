package com.example.hw_25_spring_data.model.service;

import com.example.hw_25_spring_data.dto.OrderDto;
import com.example.hw_25_spring_data.dto.ProductDto;
import com.example.hw_25_spring_data.model.entities.Order;
import com.example.hw_25_spring_data.model.entities.Product;
import com.example.hw_25_spring_data.model.repo.OrderRepo;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService extends AbstractService {

    private final OrderRepo orderRepo;
    private final ProductService productService;

    /**
     * Метод принимает массив id продуктов и добавляет их в новый заказ
     * @param productId массив id продуктов
     * @return возвращает строку с описанием добавленного заказа
     */
    public String addOrder(int... productId) {
        List<ProductDto> productDtoList = new ArrayList<>();

        Arrays.stream(productId).forEach(id -> productDtoList.add(productService.getProductById(id)));

        double cost = productDtoList.stream().mapToDouble(ProductDto::getCost).sum();

        OrderDto orderDto = new OrderDto(null, productDtoList, cost);

        List<Product> productList = productDtoList.stream()
                .map(productService::convertProductDtoToProduct)
                .toList();

        Order order = convertDtoToOrder(orderDto);
        order.setProducts(productList);
        orderRepo.save(order);
        orderDto.setId(order.getId());
        return getMessage("Added new order: " + orderDto);
    }

    /**
     * Метод выдает все имеющиеся заказы
     * @return возвращает List заказов
     */
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = Streams.stream(orderRepo.findAll()).toList();
        for (Order order : orderList) {
            List<ProductDto> productDtoList = order.getProducts().stream()
                    .map(productService::convertProductToDto)
                    .toList();
            OrderDto orderDto = convertOrderToDto(order);
            orderDto.setProducts(productDtoList);
            orderDtoList.add(orderDto);
        }
        orderDtoList.forEach(orderDto -> log.info(orderDto.toString()));
        return orderDtoList;
    }

    /**
     * Выдает один заказ по его id
     * @param id принимает id заказа
     * @return возвращает заказ
     */
    public OrderDto getOrderById(int id) {
        OrderDto orderDto = convertOrderToDto(getOrder(id));
        log.info("{}", orderDto);
        return orderDto;
    }

    /**
     * Удаляет заказ по id
     * @param id принимает id заказа
     * @return возвращает сообщение об удалении заказа
     */
    public String removeOrder(int id) {
        Order order = getOrder(id);
        orderRepo.delete(order);
        return getMessage("Order with id=" + id + " was removed");
    }

    /**
     * Удаляет заказ по id продукта в заказе
     * @param id принимает id продукта
     * @return возвращает сообщение об удалении заказа
     */
    public String removeOrderByProdId(int id) {
        List<OrderDto> allOrders = getAllOrders();
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (OrderDto orderDto : allOrders) {
            for (ProductDto productDto : orderDto.getProducts()) {
                if (productDto.getId() == id) {
                    orderRepo.deleteById(orderDto.getId());
                    stringJoiner.add(getMessage("Order removed: " + orderDto));
                }
            }
        }
        if (stringJoiner.length() == 0) {
            throw new NoSuchElementException("No orders with product id=" + id + " present");
        } else {
            return stringJoiner.toString();
        }
    }

    public String removeAllOrders() {
        orderRepo.deleteAll();
        return getMessage("All orders was removed");
    }

    private Order getOrder(int id) {
        return orderRepo.findById(id).orElseThrow();
    }

    private OrderDto convertOrderToDto(Order order) {
        return objectMapper.convertValue(order, OrderDto.class);
    }

    private Order convertDtoToOrder(OrderDto orderDto) {
        return objectMapper.convertValue(orderDto, Order.class);
    }

}

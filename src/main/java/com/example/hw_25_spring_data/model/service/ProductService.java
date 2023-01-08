package com.example.hw_25_spring_data.model.service;

import com.example.hw_25_spring_data.dto.ProductDto;
import com.example.hw_25_spring_data.model.entities.Product;
import com.example.hw_25_spring_data.model.repo.ProductRepo;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService extends AbstractService {

    private final ProductRepo productRepo;
    private int index = 1;

    /**
     * Добавляет указанное количество продуктов, создаваемых с шаблонным названием и ценой
     * @param quantity количество продуктов для создания
     * @return возвращает сообщение с описанием добавленных продуктов
     */
    public String addProducts(int quantity) {
        StringJoiner msg = new StringJoiner("\n");
        for (int i = quantity; i > 0; i--) {
            String name = String.format("product-%d", index);
            double cost = Double.parseDouble(String.valueOf(index) + index + "." + index + index);

            ProductDto productDto = new ProductDto(null, name, cost);
            msg.add(addProduct(productDto));
            index++;
        }
        return msg.toString();
    }

    /**
     * Возвращает список всех продуктов
     * @return Возвращает List всех продуктов
     */
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtoList = Streams.stream(productRepo.findAll())
                .map(this::convertProductToDto)
                .toList();
        productDtoList.forEach(productDto -> log.info(productDto.toString()));
        return productDtoList;
    }

    /**
     * Возвращает продукт по его id
     * @return Возвращает продукт по его id
     */
    public ProductDto getProductById(int id) {
        ProductDto productDto = convertProductToDto(productRepo.findById(id).orElseThrow());
        log.info("{}", productDto);
        return productDto;
    }

    public String removeAllProducts() {
        productRepo.deleteAll();
        return getMessage("All products was removed");
    }

    private String addProduct(ProductDto dto) {
        Product product = convertProductDtoToProduct(dto);
        productRepo.save(product);
        dto.setId(product.getId());
        return getMessage("Added new product: " + dto);
    }

    protected ProductDto convertProductToDto(Product product) {
        return objectMapper.convertValue(product, ProductDto.class);
    }

    protected Product convertProductDtoToProduct(ProductDto productDto) {
        return objectMapper.convertValue(productDto, Product.class);
    }

}

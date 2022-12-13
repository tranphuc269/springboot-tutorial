package com.learn.cqrs_pattern.queries.api.projection;


import com.learn.cqrs_pattern.command.api.data.ProductModel;
import com.learn.cqrs_pattern.command.api.data.ProductRepository;
import com.learn.cqrs_pattern.command.api.model.ProductRestModel;
import com.learn.cqrs_pattern.queries.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    @Autowired
    private ProductRepository productRepository;

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery query) {
        List<ProductModel> products = productRepository.findAll();
        List<ProductRestModel> productRestModels = products
                .stream()
                .map(product ->
                        ProductRestModel
                                .builder()
                                .price(product.getPrice())
                                .quantity(product.getQuantity())
                                .name(product.getName())
                                .build())
                .collect(Collectors.toList());
        return productRestModels;
    }

}

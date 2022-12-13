package com.learn.cqrs_pattern.queries.api.controller;

import com.learn.cqrs_pattern.command.api.model.ProductRestModel;
import com.learn.cqrs_pattern.queries.api.queries.GetProductsQuery;
import lombok.NoArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@NoArgsConstructor
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    List<ProductRestModel> getAllProducts() {
        GetProductsQuery getProductsQuery = new GetProductsQuery();
        return queryGateway
                .query(getProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                .join();
    }

}

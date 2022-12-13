package com.learn.cqrs_pattern.command.api.events;


import com.learn.cqrs_pattern.command.api.data.ProductModel;
import com.learn.cqrs_pattern.command.api.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {
    @Autowired
    private ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductModel product = new ProductModel();
        BeanUtils.copyProperties(event, product);
        System.out.println("product : " + product.getProductId());
        productRepository.save(product);
    }
}

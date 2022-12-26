package com.bookstore.orderservice.application.es.cart_item.command.api.events.handler;

import com.bookstore.common.domain.exception.BusinessError;
import com.bookstore.common.domain.exception.ExceptionCommon;
import com.bookstore.orderservice.application.es.cart_item.command.api.events.AddCartItemEvent;
import com.bookstore.orderservice.application.es.cart_item.command.api.events.RemoveAllCartItemEvent;
import com.bookstore.orderservice.application.es.cart_item.command.api.events.RemoveCartItemEvent;
import com.bookstore.orderservice.application.es.cart_item.command.model.CartItemCommandRepository;
import com.bookstore.orderservice.application.es.cart_item.command.model.entity.CartItemCommandEntity;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class CartItemHandler implements ICartItemHandler {


    @Autowired
    private CartItemCommandRepository repository;


    @EventHandler
    @Override
    public void on(AddCartItemEvent event) {
        List<CartItemCommandEntity> carts = repository.findAllByCartId(event.getCartId());
        if (carts
                .stream()
                .map(CartItemCommandEntity::getProductId)
                .collect(Collectors
                        .toList())
                .contains(event.getProductId())) {
            CartItemCommandEntity entity =
                    repository.findByProductId(event.getProductId()).get();
            entity.setQuantity(entity.getQuantity() + 1);
            repository.save(entity);
        } else {
            CartItemCommandEntity entity = CartItemCommandEntity
                    .builder()
                    .cartId(event.getCartId())
                    .productId(event.getProductId())
                    .quantity(event.getQuantity())
                    .productName(event.getProductName())
                    .images(event.getImages())
                    .productPrice(event.getProductPrice())
                    .productShortContent(event.getProductShortDescription())
                    .build();
            entity.setStatus(1);
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            repository.save(entity);
        }

    }

    @EventHandler
    @Override
    public void on(RemoveCartItemEvent event) {
        Optional<CartItemCommandEntity> entityOptional =
                repository.findById(event.getCartItemId());
        if (entityOptional.isEmpty()) {
            throw new ExceptionCommon(new BusinessError(404,
                    "Not found cart item",
                    HttpStatus.NOT_FOUND));
        } else {
            repository.delete(entityOptional.get());
        }
    }


    @EventHandler
    @Override
    public void on(RemoveAllCartItemEvent event) {
        repository.deleteAllByCartId(event.getCartId());
    }
}

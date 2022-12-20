package com.bookstore.orderservice.cart_item.command.api.events.handler;

import com.bookstore.common.domain.exception.BusinessError;
import com.bookstore.common.domain.exception.ExceptionCommon;
import com.bookstore.orderservice.cart_item.command.api.events.CreateCartItemEvent;
import com.bookstore.orderservice.cart_item.command.api.events.RemoveAllCartItemEvent;
import com.bookstore.orderservice.cart_item.command.api.events.RemoveCartItemEvent;
import com.bookstore.orderservice.cart_item.command.api.events.UpdateCartItemEvent;
import com.bookstore.orderservice.cart_item.command.model.CartItemCommandRepository;
import com.bookstore.orderservice.cart_item.command.model.entity.CartItemCommandEntity;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;


@Component
public class CartItemHandler implements ICartItemHandler {


    @Autowired
    private CartItemCommandRepository repository;


    @EventHandler
    @Override
    public void on(CreateCartItemEvent event) {
        CartItemCommandEntity entity = CartItemCommandEntity
                .builder()
                .cartId(event.getCartId())
                .productId(event.getProductId())
                .quantity(event.getQuantity())
                .productName(event.getProductName())
                .images(event.getImages())
                .productPrice(event.getProductPrice())
                .build();
        entity.setStatus(1);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        repository.save(entity);
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


    @Override
    @EventHandler
    public void on(UpdateCartItemEvent event) {
        Optional<CartItemCommandEntity> entityOptional =
                repository.findById(event.getCartItemId());
        if (entityOptional.isEmpty()) {
            throw new ExceptionCommon(new BusinessError(404,
                    "Not found cart item",
                    HttpStatus.NOT_FOUND));
        } else {
            entityOptional.get().setUpdateTime(new Date());
            entityOptional.get().setQuantity(event.getQuantity());
            repository.save(entityOptional.get());
        }
    }

    @EventHandler
    @Override
    public void on(RemoveAllCartItemEvent event) {
        repository.deleteAllByCartId(event.getCartId());
    }
}

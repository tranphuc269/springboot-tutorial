package com.bookstore.orderservice.cart.query.api.projection;


import com.bookstore.orderservice.cart.query.api.queries.GetCartQuery;
import com.bookstore.orderservice.cart.query.model.CartQueryRepository;
import com.bookstore.orderservice.cart.query.model.dto.CartResponse;
import com.bookstore.orderservice.cart.query.model.entity.CartQueryEntity;
import com.bookstore.orderservice.cart_item.query.api.queries.GetListCartItemQuery;
import com.bookstore.orderservice.cart_item.query.model.dto.CartItemResponse;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class CartProjection {
    @Autowired
    private CartQueryRepository cartRepository;

    @Autowired
    private QueryGateway queryGateway;

    @QueryHandler
    public CartResponse handle(GetCartQuery query) {
        List<CartItemResponse> cartItemResponses = queryGateway.query(GetListCartItemQuery
                        .builder()
                        .cartId(query
                                .getCartId())
                        .build(), ResponseTypes.multipleInstancesOf(CartItemResponse.class))
                .join();
        CartQueryEntity cartEntity = cartRepository.findById(query.getCartId()).get();

        AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);

        cartItemResponses.forEach(cartItem -> {
            totalPrice.updateAndGet(v -> (v + cartItem.getProductPrice() * cartItem.getQuantity()));
        });


        CartResponse cart = CartResponse
                .builder()
                .cartId(cartEntity.getCartId())
                .items(cartItemResponses)
                .totalPrice(totalPrice.get())
                .build();
        return cart;
    }
}
package com.bookstore.orderservice.cart.command.model.entity;
import com.bookstore.common.infrastructure.repo.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartCommandEntity extends BaseModel {
    @Id
    @GeneratedValue(generator="cart-uuid")
    @GenericGenerator(name="cart-uuid", strategy = "uuid")
    @Column(name = "cart_id", updatable = false, nullable = false)
    private String cartId;

    @Column(name = "user_id", updatable = false, nullable = false)
    private String userId;
    @Column(name = "total_price", updatable = false, nullable = false)
    private BigDecimal totalPrice;
}

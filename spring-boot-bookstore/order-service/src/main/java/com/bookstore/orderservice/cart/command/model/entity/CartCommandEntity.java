package com.bookstore.orderservice.cart.command.model.entity;
import com.bookstore.common.infrastructure.repo.model.BaseModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartCommandEntity extends BaseModel {
    @Id
    @GeneratedValue(generator="cart-uuid")
    @GenericGenerator(name="cart-uuid", strategy = "uuid")
    @Column(name = "cart_id", updatable = false, nullable = false)
    private String cartId;

    @Column(name = "user_id", updatable = false, nullable = false)
    private String userId;
}

package com.bookstore.orderservice.cart_item.command.model.entity;


import com.bookstore.common.infrastructure.repo.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemCommandEntity extends BaseModel {

    @Id
    @GeneratedValue(generator="cart_item_id-uuid")
    @GenericGenerator(name="cart_item_id-uuid", strategy = "uuid")
    @Column(name = "cart_item_id", updatable = false, nullable = false)
    private String cartItemId;

    @Column(name = "product_id", updatable = false, nullable = false)
    private String productId;
    @Column(name = "product_price", updatable = false, nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_name", updatable = false, nullable = false)
    private String productName;

    @Column(name = "product_short_content", updatable = false, nullable = false)
    private String productShortContent;

    @Column(name = "images", updatable = false, nullable = false)
    private String images;

    @Column(name = "cart_id", updatable = false, nullable = false)
    private String cartId;
    @Column(name = "quantity", updatable = false, nullable = false)
    private int quantity;


}

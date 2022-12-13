package com.learn.cqrs_pattern.command.api.data;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductModel {

    @Id
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

}

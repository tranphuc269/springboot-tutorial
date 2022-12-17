package com.bookstore.catalogservice.product.query.api.queries;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@Data
public class GetDetailProductQuery {
    private String productId;
}

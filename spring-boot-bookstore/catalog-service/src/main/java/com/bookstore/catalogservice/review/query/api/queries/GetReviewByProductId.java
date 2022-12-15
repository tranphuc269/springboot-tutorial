package com.bookstore.catalogservice.review.query.api.queries;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class GetReviewByProductId {
    private String productId;
}

package com.bookstore.catalogservice.product.query.api.controller;


import com.bookstore.catalogservice.product.query.api.queries.GetDetailProductQuery;
import com.bookstore.catalogservice.product.query.read_model.response.ProductDetailResponse;
import com.bookstore.common.application.response.dto.BaseResponse;
import com.bookstore.common.domain.exception.BusinessError;
import com.bookstore.common.domain.exception.ExceptionCommon;
import lombok.NoArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@RequestMapping("/product")
public class ProductQueryController {

    @Autowired
    private QueryGateway gateway;

    @GetMapping("/{productId}")
    public ResponseEntity<BaseResponse<Object>> getDetailProduct(@PathVariable String productId) {
        try {
            GetDetailProductQuery query = GetDetailProductQuery
                    .builder()
                    .productId(productId)
                    .build();

            return ResponseEntity.ok(BaseResponse
                    .ofSucceeded(
                            gateway
                                    .query(query,
                                            ResponseTypes.instanceOf(ProductDetailResponse.class))
                                    .join()));
        } catch (Exception ignored) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse
                    .ofFailed(new BusinessError(404,
                            ignored.getMessage(), HttpStatus.NOT_FOUND)));
        }
    }

}

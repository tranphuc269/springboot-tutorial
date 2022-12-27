package com.bookstore.catalogservice.application.es.product.query.api.controller;


import com.bookstore.catalogservice.application.es.product.query.api.queries.AddProductToCartQuery;
import com.bookstore.catalogservice.application.es.product.query.api.queries.GetDetailProductQuery;
import com.bookstore.catalogservice.application.es.product.query.api.queries.ProductsFilterQuery;
import com.bookstore.catalogservice.application.es.product.query.read_model.query_request.ProductAddToCartRequest;
import com.bookstore.catalogservice.application.es.product.query.read_model.query_request.ProductFilterRequest;
import com.bookstore.catalogservice.application.es.product.query.read_model.response.ProductDetailResponse;
import com.bookstore.catalogservice.application.es.product.query.read_model.response.ProductResponse;
import com.bookstore.common.application.response.dto.BaseResponse;
import com.bookstore.common.domain.exception.BusinessError;
import com.bookstore.common.utils.CommonUtilityMethods;
import lombok.NoArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/filter")
    public ResponseEntity<Object> getFilterProducts(@RequestBody ProductFilterRequest request) {
        ProductsFilterQuery query = ProductsFilterQuery
                .builder()
                .categoryId(request.getCategoryId())
                .authorId(request.getAuthorId())
                .sort(request.getSort())
                .limit(request.getLimit())
                .offset(request.getOffset())
                .build();
        return ResponseEntity.ok(BaseResponse
                .ofSucceeded(
                        gateway
                                .query(query,
                                        ResponseTypes.multipleInstancesOf(ProductResponse.class))
                                .join()));
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<Object> addProductToCart(@RequestBody ProductAddToCartRequest request) {
        System.out.println("Create cart");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdFromToken = CommonUtilityMethods.getUserIdFromToken(authentication);
        AddProductToCartQuery query = AddProductToCartQuery
                .builder()
                .userId(userIdFromToken)
                .productId(request.getProductId())
                .build();
        return ResponseEntity.ok(BaseResponse
                .ofSucceeded(
                        gateway
                                .query(query,
                                        ResponseTypes.instanceOf(String.class))
                                .join()));
    }

}

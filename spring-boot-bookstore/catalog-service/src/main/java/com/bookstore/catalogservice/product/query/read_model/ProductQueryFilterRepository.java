package com.bookstore.catalogservice.product.query.read_model;

import com.bookstore.catalogservice.product.query.api.queries.GetProductsValue;
import com.bookstore.catalogservice.product.query.read_model.entity.ProductQueryEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductQueryFilterRepository {
    List<ProductQueryEntity> findProductQueryWithFilterPageable(GetProductsValue filter);
}

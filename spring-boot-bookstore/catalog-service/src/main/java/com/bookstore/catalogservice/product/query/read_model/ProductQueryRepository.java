package com.bookstore.catalogservice.product.query.read_model;

import com.bookstore.catalogservice.product.query.read_model.entity.ProductQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQueryRepository extends JpaRepository<ProductQueryEntity, String> {
}

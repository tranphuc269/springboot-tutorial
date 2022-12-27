package com.bookstore.catalogservice.application.es.product.query.read_model;

import com.bookstore.catalogservice.application.es.product.query.read_model.entity.ProductQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductQueryRepository extends JpaRepository<ProductQueryEntity, String>,
        JpaSpecificationExecutor<ProductQueryEntity> {
}

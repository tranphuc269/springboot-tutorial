package com.bookstore.catalogservice.product.query.read_model;

import com.bookstore.catalogservice.product.query.read_model.entity.ProductQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductQueryRepository extends JpaRepository<ProductQueryEntity, String>,
        JpaSpecificationExecutor<ProductQueryEntity> {
    List<ProductQueryEntity> findAllByAuthorId(String authorId, Pageable pageable);

    List<ProductQueryEntity> findAllByCategoryId(String categoryId, Pageable pageable);
}

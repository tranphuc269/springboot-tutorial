package com.bookstore.catalogservice.product.command.write_model;

import com.bookstore.catalogservice.product.command.write_model.entity.ProductCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductCommandRepository
        extends JpaRepository<ProductCommandEntity, String> {
}

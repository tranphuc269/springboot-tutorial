package com.bookstore.catalogservice.attribute.data.repository;

import com.bookstore.catalogservice.attribute.data.model.AttributeValueModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValueRepository extends JpaRepository<AttributeValueModel, String> {
}

package com.bookstore.catalogservice.attribute.repository;

import com.bookstore.catalogservice.attribute.command.write_data.entity.AttributeValueModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValueRepository extends JpaRepository<AttributeValueModel, String> {
}

package com.bookstore.catalogservice.attribute.data.repository;


import com.bookstore.catalogservice.attribute.data.model.AttributeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeModel, String> {
}

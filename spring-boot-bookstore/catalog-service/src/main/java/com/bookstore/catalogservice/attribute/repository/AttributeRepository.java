package com.bookstore.catalogservice.attribute.repository;


import com.bookstore.catalogservice.attribute.command.write_data.entity.AttributeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeModel, String> {
}

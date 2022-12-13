package com.bookstore.catalogservice.category.command.api.data;

import com.bookstore.catalogservice.category.command.api.data.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, String> {
}

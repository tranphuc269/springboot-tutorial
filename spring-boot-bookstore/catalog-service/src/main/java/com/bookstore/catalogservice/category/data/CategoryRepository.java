package com.bookstore.catalogservice.category.data;

import com.bookstore.catalogservice.category.data.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, String> {
}

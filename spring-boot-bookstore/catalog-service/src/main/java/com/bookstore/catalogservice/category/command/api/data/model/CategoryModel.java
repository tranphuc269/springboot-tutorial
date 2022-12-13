package com.bookstore.catalogservice.category.command.api.data.model;

import com.bookstore.common.infrastructure.repo.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "categories")
@Getter
@Setter
public class CategoryModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false, nullable = false)
    private String categoryId;

    @Column(name = "category_name", nullable = false, columnDefinition = "TEXT")
    private String categoryName;

    private String description;
    @Column(name = "image", columnDefinition = "TEXT", length = 100000)
    private String image;

    @Id
    public String getId() {
        return categoryId;
    }

    public void setId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}

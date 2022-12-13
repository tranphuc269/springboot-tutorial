package com.bookstore.catalogservice.category.command.api.events;


import com.bookstore.catalogservice.category.command.api.data.CategoryRepository;
import com.bookstore.catalogservice.category.command.api.data.model.CategoryModel;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryEventHandler {
    @Autowired
    private CategoryRepository categoryRepository;


    @EventHandler
    public void on(CategoryCreateEvent event){
        System.out.println("Create event save category");
        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(event, categoryModel);
        categoryRepository.save(categoryModel);
    }
}

package com.bookstore.catalogservice.attribute.command.api.events.attribute;

import com.bookstore.catalogservice.attribute.data.model.AttributeModel;
import com.bookstore.catalogservice.attribute.data.repository.AttributeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AttributeEventHandler {
    @Autowired
    private AttributeRepository repository;


    @EventHandler
    public void on(CreateAttributeEvent event){
        AttributeModel model = new AttributeModel();
        BeanUtils.copyProperties(event, model);
        System.out.println("Event create attribute value");
        repository.save(model);
    }
}

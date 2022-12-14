package com.bookstore.catalogservice.attribute.command.api.events.attributevalue;

import com.bookstore.catalogservice.attribute.data.model.AttributeValueModel;
import com.bookstore.catalogservice.attribute.data.repository.AttributeValueRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class AttributeValueEventHandler {
    @Autowired
    private AttributeValueRepository repository;

    @EventHandler
    public void on(CreateAttributeValueEvent event) {
        System.out.println("Create attribute value event");
        AttributeValueModel model = new AttributeValueModel();

        BeanUtils.copyProperties(event, model);

        repository.save(model);

    }


}

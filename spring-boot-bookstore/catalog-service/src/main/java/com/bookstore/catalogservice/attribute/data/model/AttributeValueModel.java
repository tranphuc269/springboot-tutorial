package com.bookstore.catalogservice.attribute.data.model;


import com.bookstore.common.infrastructure.repo.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "attribute_values")
public class AttributeValueModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_value_id", updatable = false, nullable = false)
    private String id;


    @Column(name = "attribute_id", updatable = false, nullable = false)
    private String attributeId;

    private String value;
    @Column(name = "product_id", updatable = false, nullable = false)
    private String productId;
}

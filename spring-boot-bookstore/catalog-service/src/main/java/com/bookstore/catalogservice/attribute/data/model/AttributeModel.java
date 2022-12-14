package com.bookstore.catalogservice.attribute.data.model;


import com.bookstore.common.infrastructure.repo.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "attributes")
@Getter
@Setter
public class AttributeModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id", updatable = false, nullable = false)
    private String id;

    private String name;

}

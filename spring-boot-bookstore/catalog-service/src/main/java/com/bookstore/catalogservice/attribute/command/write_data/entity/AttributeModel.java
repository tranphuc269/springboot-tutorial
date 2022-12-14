package com.bookstore.catalogservice.attribute.command.write_data.entity;


import com.bookstore.common.infrastructure.repo.model.BaseModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "attributes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttributeModel extends BaseModel {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "attribute_id", updatable = false, nullable = false)
    private String attributeId;

    private String name;
}

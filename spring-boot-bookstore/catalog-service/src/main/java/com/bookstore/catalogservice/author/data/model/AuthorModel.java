package com.bookstore.catalogservice.author.data.model;


import com.bookstore.common.infrastructure.repo.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorModel extends BaseModel {
    @Id
    @GeneratedValue(generator = "author-uuid")
    @GenericGenerator(name = "author-uuid", strategy = "uuid")
    @Column(name = "author_id", updatable = false, nullable = false)
    private String authorId;

    @Column(name = "images", columnDefinition = "TEXT", length = 10000)
    private String images;

    @Column(name = "name", columnDefinition = "TEXT", length = 10000)
    private String name;
    @Column(name = "description", columnDefinition = "TEXT", length = 10000)
    private String description;

    public List<String> getListImages() {
        List<String> imgs = new ArrayList<>
                (List
                        .of(this.getImages()
                        .replace(']', ' ')
                                .replace('[', ' ')
                                .trim()
                                .split(",")));
        imgs.replaceAll(String::trim);
        return imgs;
    }
}

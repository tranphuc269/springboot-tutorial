package com.bookstore.catalogservice.review.query.read_model;


import com.bookstore.catalogservice.review.query.read_model.entity.ReviewQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewQueryRepository extends JpaRepository<ReviewQueryEntity, String> {
    List<ReviewQueryEntity> findAllByProductId(String productId);
}

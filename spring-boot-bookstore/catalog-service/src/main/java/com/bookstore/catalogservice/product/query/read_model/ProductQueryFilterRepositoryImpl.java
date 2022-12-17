package com.bookstore.catalogservice.product.query.read_model;

import com.bookstore.catalogservice.product.query.api.queries.GetProductsValue;
import com.bookstore.catalogservice.product.query.read_model.entity.ProductQueryEntity;
import com.bookstore.catalogservice.product.query.read_model.specifications.ProductQuerySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.print.Pageable;
import java.util.List;


@Repository
public class ProductQueryFilterRepositoryImpl implements ProductQueryFilterRepository {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductQueryRepository productRepository;

    @Override
    public List<ProductQueryEntity>
    findProductQueryWithFilterPageable(GetProductsValue filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = cb.createQuery();
        Root<ProductQueryEntity> products = criteriaQuery.from(ProductQueryEntity.class);
        Specification<ProductQueryEntity> queryByCategoryId = ProductQuerySpecification
                .hasCategoryId(filter.getCategoryId());
        Specification<ProductQueryEntity> queryByAuthorId = ProductQuerySpecification
                .hasAuthorId(filter.getAuthorId());
        List<ProductQueryEntity> list = productRepository
                .findAll(Specification.where(queryByCategoryId.and(queryByAuthorId)));
        return list;
    }
}

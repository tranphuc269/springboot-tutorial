package com.bookstore.catalogservice.product.query.api.projection;


import com.bookstore.catalogservice.attribute.command.write_data.entity.AttributeModel;
import com.bookstore.catalogservice.attribute.command.write_data.entity.AttributeValueModel;
import com.bookstore.catalogservice.attribute.repository.AttributeRepository;
import com.bookstore.catalogservice.attribute.repository.AttributeValueRepository;
import com.bookstore.catalogservice.author.data.AuthorRepository;
import com.bookstore.catalogservice.author.data.dto.AuthorResponse;
import com.bookstore.catalogservice.author.data.model.AuthorModel;
import com.bookstore.catalogservice.category.data.CategoryRepository;
import com.bookstore.catalogservice.category.data.dto.CategoryResponse;
import com.bookstore.catalogservice.category.data.model.CategoryModel;
import com.bookstore.catalogservice.infrastructure.CatalogKafkaProducer;
import com.bookstore.catalogservice.product.query.api.queries.AddProductToCartQuery;
import com.bookstore.catalogservice.product.query.api.queries.GetDetailProductQuery;
import com.bookstore.catalogservice.product.query.api.queries.ProductsFilterQuery;
import com.bookstore.catalogservice.product.query.read_model.ProductQueryRepository;
import com.bookstore.catalogservice.product.query.read_model.entity.ProductQueryEntity;
import com.bookstore.catalogservice.product.query.read_model.response.AttributeProductResponse;
import com.bookstore.catalogservice.product.query.read_model.response.ProductDetailResponse;
import com.bookstore.catalogservice.product.query.read_model.response.ProductResponse;
import com.bookstore.catalogservice.product.query.read_model.specifications.ProductQuerySpecification;
import com.bookstore.catalogservice.review.query.read_model.ReviewQueryRepository;
import com.bookstore.common.domain.exception.BusinessError;
import com.bookstore.common.domain.exception.ExceptionCommon;
import com.bookstore.common.infrastructure.kafka.product_order.KafkaSendProductCreateCartItem;
import com.bookstore.common.utils.KafkaTopicUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    @Autowired
    private ProductQueryRepository productRepository;

    @Autowired
    private ReviewQueryRepository reviewRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AttributeValueRepository attributeValueRepository;

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CatalogKafkaProducer kafkaProducer;


    // query detail product
    @QueryHandler
    public ProductDetailResponse handle(GetDetailProductQuery query) {

        Optional<ProductQueryEntity> productQuery = productRepository.findById(query.getProductId());
//        if (productQuery.isEmpty()) {
//            throw new ExceptionCommon(new BusinessError(404,
//                    "Not found product", HttpStatus.NOT_FOUND));
//        }
        // get author
        AuthorModel authorModel = authorRepository
                .findById(productQuery.get().getAuthorId()).get();
        // get category
        CategoryModel categoryModel = categoryRepository
                .findById(productQuery.get().getCategoryId()).get();
        // get list attribute value
        List<AttributeProductResponse> attributes = new ArrayList<>();
        List<AttributeValueModel> attributeValueList = attributeValueRepository
                .getAttributeValueModelsByProductId(
                        query.getProductId());
        // get list attribute
        List<AttributeModel> attributeList = attributeRepository.
                findAllByAttributeIds(attributeValueList.stream()
                        .map(AttributeValueModel::getAttributeId)
                        .collect(Collectors.toList()));
        // map attribute to attribute response
        attributeList.forEach(attribute -> {
            AttributeProductResponse attributeProduct = new AttributeProductResponse();
            attributeProduct.setProductId(query.getProductId());
            attributeProduct.setAttributeId(attribute.getAttributeId());
            attributeProduct.setName(attribute.getName());
            Optional<AttributeValueModel> result = Optional.ofNullable(attributeValueList.stream().filter(attributeModelList ->
                    attributeModelList.getAttributeId().equals(attribute.getAttributeId())).findFirst().orElse(null));
            AttributeValueModel attributeValueModel = result.get();
            attributeProduct.setValue(attributeValueModel.getValue());
            attributes.add(attributeProduct);
        });
        // map data to product detail
        ProductDetailResponse
                productDetailResponse = ProductDetailResponse
                .builder()
                .productId(productQuery.get().getProductId())
                .attributes(attributes)
                .name(productQuery.get().getName())
                .author(AuthorResponse
                        .builder()
                        .authorId(authorModel.getAuthorId())
                        .name(authorModel.getName())
                        .images(authorModel.getListImages())
                        .build())
                .category(CategoryResponse
                        .builder()
                        .categoryId(categoryModel.getCategoryId())
                        .categoryName(categoryModel.getCategoryName())
                        .image(categoryModel.getImage())
                        .description(categoryModel.getDescription())
                        .build())
                .price(productQuery.get().getPrice())
                .availableItemCount(productQuery.get().getAvailableItemCount())
                .shortContent(productQuery.get().getShortContent())
                .description(productQuery.get().getDescription())
                .images(productQuery.get().getListImages())
                .quantitySold(productQuery.get().getQuantitySold())
                .build();

        return productDetailResponse;
    }


    @QueryHandler
    public List<ProductResponse> handle(ProductsFilterQuery query) {

        Specification<ProductQueryEntity> queryByCategoryId = ProductQuerySpecification
                .hasCategoryId(query.getCategoryId());
        Specification<ProductQueryEntity> queryByAuthorId = ProductQuerySpecification
                .hasAuthorId(query.getAuthorId());
        Pageable pageable = PageRequest.of(query.getOffset(), query.getLimit());
        List<ProductQueryEntity> products = productRepository
                .findAll(Specification.where(queryByCategoryId.and(queryByAuthorId)),
                        pageable).stream().collect(Collectors.toList());
        return products.stream()
                .map(productQueryEntity -> ProductResponse
                        .builder()
                        .productId(productQueryEntity.getProductId())
                        .price(productQueryEntity.getPrice())
                        .images(productQueryEntity.getImages())
                        .quantitySold(productQueryEntity.getQuantitySold())
                        .availableItemCount(productQueryEntity.getAvailableItemCount())
                        .shortContent(productQueryEntity.getShortContent())
                        .name(productQueryEntity.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @QueryHandler
    public String handle(AddProductToCartQuery query){
        Optional<ProductQueryEntity> entityOptional =
                productRepository.findById(query.getProductId());
//        if(entityOptional.isEmpty()){
//            throw new RuntimeException("Not found product");
//        }
        kafkaProducer.send(KafkaTopicUtils.TOPIC_ADD_TO_CART,
                KafkaSendProductCreateCartItem
                        .builder()
                        .cartId(query.getCartId())
                        .quantity(1)
                        .productId(entityOptional.get().getProductId())
                        .productName(entityOptional.get().getName())
                        .images(entityOptional.get().getImages())
                        .productShortDescription(entityOptional.get().getShortContent())
                        .productPrice(entityOptional.get().getPrice())
                        .build());
        return "ok";
    }
}
package com.myretail.products.dao;

import com.myretail.products.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, Long> {

    public ProductEntity findByProductId(Long productID);
}

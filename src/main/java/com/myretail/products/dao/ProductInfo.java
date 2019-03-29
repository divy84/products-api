package com.myretail.products.dao;

import com.myretail.products.entity.ProductEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface ProductInfo {
    ProductEntity getProductInfo(Long productId);

}

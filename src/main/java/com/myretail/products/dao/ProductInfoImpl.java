package com.myretail.products.dao;

import com.myretail.products.entity.ProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoImpl implements ProductInfo {

 private static final Logger logger = LoggerFactory.getLogger(ProductInfoImpl.class);

  private ProductEntity productEntity;
  private ProductRepository productRepository;


  public ProductInfoImpl(ProductRepository productRepository , ProductEntity productEntity)
  {
      this.productRepository = productRepository;
      this.productEntity = productEntity;
  }

  public ProductEntity getProductInfo(Long productId) throws RuntimeException
  {
      try
      {
          productEntity = productRepository.findByProductId(productId);

          if (productEntity == null) {
              logger.error("Product pricing information is not available");
              throw new RuntimeException("Pricing Details missing for Product:");
          }

      } catch(Exception e)
      {
          logger.error("Database Exception while retrieving Product Pricing Details");
          throw new RuntimeException("Exception in Product Respository:" + e.getMessage());
      }

      return productEntity;
  }
}

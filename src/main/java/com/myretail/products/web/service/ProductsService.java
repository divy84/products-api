package com.myretail.products.web.service;

import com.myretail.products.api.ProductDescFetcher;
import com.myretail.products.dao.ProductInfo;
import com.myretail.products.dto.PricingResponseDTO;
import com.myretail.products.dto.ProductResponseDTO;
import com.myretail.products.entity.ProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    private static final Logger logger = LoggerFactory.getLogger(ProductsService.class);

    PricingResponseDTO pricingResponseDTO = new PricingResponseDTO();

    private ProductDescFetcher productDescFetcher;
    private ProductInfo productInfo;


    public ProductsService(ProductDescFetcher productDescFetcher,ProductInfo productInfo  )
    {
        this.productDescFetcher = productDescFetcher;
        this.productInfo = productInfo;

    }

    public ProductResponseDTO getProductDetails(Long ProductID) throws RuntimeException
    {
      ProductResponseDTO productResponseDTO = new ProductResponseDTO();

      String productDesc = getProductDesc(ProductID);
      logger.info("Product description is : "  +productDesc);

      pricingResponseDTO = getProductPricing(ProductID);


      productResponseDTO.setId(ProductID);

      productResponseDTO.setName(productDesc);

      productResponseDTO.setPricingResponseDTO(pricingResponseDTO);

      return productResponseDTO;
    }

    private String getProductDesc(Long productID) throws RuntimeException
    {
        String productDesc;

        productDesc = productDescFetcher.fetchProductInfo(productID);

        return productDesc;

    }

    private PricingResponseDTO getProductPricing(Long productID) throws RuntimeException
    {
        ProductEntity productEntity = productInfo.getProductInfo(productID);

        pricingResponseDTO.setValue(productEntity.getRetail());
        pricingResponseDTO.setCurrencyCode(productEntity.getCurrencyCode());

        return pricingResponseDTO;
    }
}

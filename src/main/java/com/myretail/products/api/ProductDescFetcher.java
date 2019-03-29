package com.myretail.products.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


@Component
public class ProductDescFetcher {

    private static final Logger logger = LoggerFactory.getLogger(ProductDescFetcher.class);

    @Value("${app.productInfoAPI.url}")
    private String productInfoAPIurl;

    private APICaller apiCaller;

    public ProductDescFetcher(APICaller apiCaller)
    {
        this.apiCaller = apiCaller;
    }

    public String fetchProductInfo(Long productId) throws APICallerException
    {
        String productDesc;
        String productDescURL = productInfoAPIurl+productId.toString();

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            HttpEntity httpEntity = new HttpEntity((httpHeaders));

            JsonNode apiResponse = apiCaller.invokeAPI(productDescURL, httpEntity, HttpMethod.GET);
            productDesc = apiResponse.path("product").path("item").path("product_description").path("title").asText();

            return productDesc;

        } catch (Exception e)
        {
            logger.error("Exception while fetching Product Description for product " +productId);
            throw new APICallerException("Exception while calling product API");
        }
    }

}

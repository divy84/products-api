package com.myretail.products.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

@Component
public class APICaller {

    private static final Logger logger = LoggerFactory.getLogger(ProductDescFetcher.class);

    private RestTemplate restTemplate;

    public APICaller(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public JsonNode invokeAPI(String url , HttpEntity httpEntity , HttpMethod httpMethod) throws APICallerException
    {
        JsonNode apiResponse;

        try
        {
            ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(url , httpMethod , httpEntity , JsonNode.class);
            apiResponse = responseEntity.getBody();

            if (apiResponse == null )
            {
                logger.error("Exception while invoking Product API . No product details present");
                throw new APICallerException("Exception when calling ProductEntity Info API. No product details present");
            }
        }catch (UnknownHttpStatusCodeException e)
        {
            logger.error("Exception while invoking Product API");
            throw new APICallerException(e.getMessage());
        }

        return apiResponse;

    }

}

package com.myretail.products.web.controller;

import com.myretail.products.dto.ProductResponseDTO;
import com.myretail.products.util.AppMetrics;
import com.myretail.products.web.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsAPIController {

    private static final Logger logger = LoggerFactory.getLogger(ProductsAPIController.class);

    private ProductsService productsService;
    private ProductResponseDTO productResponseDTO;
    private AppMetrics appMetrics;


    public ProductsAPIController(ProductsService productsService,ProductResponseDTO productResponseDTO,AppMetrics appMetrics)
    {
        this.productsService = productsService;
        this.productResponseDTO = productResponseDTO;
        this.appMetrics = appMetrics;
    }

    @RequestMapping(value="/products/{productid}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponseDTO ProductRequest(@PathVariable("productid") long productId)
    {
        try
        {
            logger.info("Incoming Product ID:" +productId);
            appMetrics.incrementIncomingRequestCounter();

            productResponseDTO = productsService.getProductDetails(productId);

            appMetrics.incrementResponseSuccessCounter();

        }catch (Exception e)
        {
            logger.error("Exception from Controller :" , e.getMessage());
            appMetrics.incrementResponseFailureCounter();
        }

        return productResponseDTO;
    }

}

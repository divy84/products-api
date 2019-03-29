package com.myretail.products.web.controller;

import com.myretail.products.dto.PricingResponseDTO;
import com.myretail.products.dto.ProductResponseDTO;
import com.myretail.products.util.AppMetrics;
import com.myretail.products.web.service.ProductsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sun.nio.cs.Surrogate.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//@RunWith(SpringJUnit4ClassRunner.class)
public class ProductsAPIControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    ProductsAPIController productsAPIController;

    @Mock
    ProductsService productsService;

    @Mock
    ProductResponseDTO productResponseDTO;

    @Mock
    AppMetrics appMetrics;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productsAPIController).build();


    }

    @Test
    public void testProductRequest() throws Exception {

       PricingResponseDTO pricingResponseDTOMock = new PricingResponseDTO();
       pricingResponseDTOMock.setValue(14.99);
       pricingResponseDTOMock.setCurrencyCode("USD");

       ProductResponseDTO productResponseDTOMock = new ProductResponseDTO();
       productResponseDTOMock.setName("TestMovie");
       productResponseDTOMock.setId(11298850L);
       productResponseDTOMock.setPricingResponseDTO(pricingResponseDTOMock);

        when(productsService.getProductDetails(11298850L)).thenReturn(productResponseDTOMock);

        mockMvc.perform(get("/products/{productID}" , 11298850L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));


    }
}

package com.myretail.products.dao;

import com.myretail.products.entity.ProductEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ProductInfoImplTest {

    private MockMvc mockMvc;

    @InjectMocks
    ProductInfoImpl productInfo;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductEntity productEntity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
       // mockMvc = MockMvcBuilders.standaloneSetup(productInfo).build();

    }

    @Test
    public void getProductInfoTest() {

        ProductEntity productEntityMock = new ProductEntity();
        productEntityMock.setRetail(17.49);
        productEntityMock.setCurrencyCode("Euro");

        when(productRepository.findByProductId(11298850L)).thenReturn(productEntityMock);

        assertTrue(isProductPricingEqual(productEntityMock , productRepository.findByProductId(11298850L)));

    }

    private boolean isProductPricingEqual(ProductEntity p1, ProductEntity p2) {

        Double a = p1.getRetail();
        Double b = p2.getRetail();

        if(a == b && p1.getCurrencyCode().equals((p2.getCurrencyCode())) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
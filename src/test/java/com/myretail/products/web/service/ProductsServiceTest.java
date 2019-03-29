package com.myretail.products.web.service;

import com.myretail.products.api.ProductDescFetcher;
import com.myretail.products.dao.ProductInfo;
import com.myretail.products.dto.PricingResponseDTO;
import com.myretail.products.dto.ProductResponseDTO;
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

public class ProductsServiceTest {

    private MockMvc mockMvc;

    @InjectMocks
    ProductsService productsService;

    @Mock
    ProductDescFetcher productDescFetcher;

    @Mock
    ProductInfo productInfo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //mockMvc = MockMvcBuilders.standaloneSetup(productsService).build();

    }

    @Test
    public void getProductDetailsTest() {

        PricingResponseDTO pricingResponseDTOMock = new PricingResponseDTO();
        pricingResponseDTOMock.setValue(17.79);
        pricingResponseDTOMock.setCurrencyCode("EURO");

        ProductResponseDTO productResponseDTOMock = new ProductResponseDTO();
        productResponseDTOMock.setName("Superbad (Blu-ray)");
        productResponseDTOMock.setId(11298850L);
        productResponseDTOMock.setPricingResponseDTO(pricingResponseDTOMock);

        ProductEntity productEntityMock = new ProductEntity();
        productEntityMock.setRetail(17.49);
        productEntityMock.setCurrencyCode("USD");

        String productDescMock = "Superbad (Blu-ray)";

        when(productDescFetcher.fetchProductInfo(11298850L)).thenReturn(productDescMock);

        when(productInfo.getProductInfo(11298850L)).thenReturn(productEntityMock);

        assertTrue(areProductsEqual(productResponseDTOMock , productsService.getProductDetails(11298850L)));

        assertTrue(isProductPricingEqual(productEntityMock , productInfo.getProductInfo(11298850L)));

    }

    private boolean areProductsEqual(ProductResponseDTO p1, ProductResponseDTO p2) {

        long a = p1.getId();
        long b = p2.getId();

        if(a == b && p1.getName().equals((p2.getName())) )
        {
            return true;
        }
        else
        {
            return false;
        }
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
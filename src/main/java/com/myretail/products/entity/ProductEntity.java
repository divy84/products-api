package com.myretail.products.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@Service
@Document(collection = "productData")
public class ProductEntity {

    @Id
    public String Id;

    public Long productId;
    public Double retail;
    public String currencyCode;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getRetail() {
        return retail;
    }

    public void setRetail(Double retail) {
        this.retail = retail;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "Id='" + Id + '\'' +
                ", productId=" + productId +
                ", retail=" + retail +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}

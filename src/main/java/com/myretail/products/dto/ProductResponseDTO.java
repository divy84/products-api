package com.myretail.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("current_price")
    private PricingResponseDTO pricingResponseDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PricingResponseDTO getPricingResponseDTO() {
        return pricingResponseDTO;
    }

    public void setPricingResponseDTO(PricingResponseDTO pricingResponseDTO) {
        this.pricingResponseDTO = pricingResponseDTO;
    }

    @Override
    public String toString() {
        return "ProductResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pricingDTO=" + pricingResponseDTO +
                '}';
    }
}


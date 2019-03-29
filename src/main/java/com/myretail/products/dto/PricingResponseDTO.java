package com.myretail.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class PricingResponseDTO {

    @JsonProperty("value")
    private double value;

    @JsonProperty("currency_code")
    private String currencyCode;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "PricingDTO{" +
                "value=" + value +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}


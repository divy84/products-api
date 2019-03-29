package com.myretail.products.api;

public class APICallerException extends RuntimeException{
    public APICallerException(String message)
    {
        super ( message);
    }
}

package com.shopino.order_service.domain.exception;

public class ProductAvailableQuantityInsufficientException extends RuntimeException {
    public ProductAvailableQuantityInsufficientException(String s) {
        super(s);
    }
}

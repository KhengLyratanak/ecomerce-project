package com.ecommerce.project.exception.Model;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {

        super(message);
    }
}

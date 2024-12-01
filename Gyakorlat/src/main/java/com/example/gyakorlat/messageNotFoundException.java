package com.example.gyakorlat;

public class messageNotFoundException extends RuntimeException {
    messageNotFoundException(Long id) {
        super("Üzenet azonostó: " + id + " nem található !");
    }
}

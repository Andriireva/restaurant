package com.example.javapractice.restaurant.service.circledepsexample;

import org.springframework.stereotype.Component;

//@Component
public class ServiceB {
    private final ServiceA serviceA;

    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}

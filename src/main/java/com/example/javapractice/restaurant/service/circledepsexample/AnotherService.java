package com.example.javapractice.restaurant.service.circledepsexample;

public class AnotherService {

    public static void main(String[] args) {
        ServiceA serviceA = new ServiceA(null);
        ServiceB serviceB = new ServiceB(serviceA);
    }
}

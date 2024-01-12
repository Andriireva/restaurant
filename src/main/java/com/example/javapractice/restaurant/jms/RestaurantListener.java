package com.example.javapractice.restaurant.jms;

import com.example.javapractice.restaurant.domain.Restaurant;
import com.example.javapractice.restaurant.exceptions.ResourceIsNotFoundException;
import com.example.javapractice.restaurant.service.IRestaurantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

// name of the class is usually
// logical queue name + listener ( consumer )
@Component
public class RestaurantListener {

    private final ObjectMapper objectMapper;
    private final IRestaurantService restaurantService;

    public RestaurantListener(ObjectMapper objectMapper,
                              IRestaurantService restaurantService) {
        this.objectMapper = objectMapper;
        this.restaurantService = restaurantService;
        System.out.println("RestaurantListener constructor" + this.objectMapper.toString());
    }

    // this method is going to be used to consume messages from "restaurants" queue
    @JmsListener(destination = "restaurants") // it is a JMS queue name
    public void listenRestaurants(Message<String> message) {
        // Expected message body example
        // {
        //      "newAddress": "another street 24",
        //      "id": 1
        // }
        String payload = message.getPayload();

        try {
            JmsRestaurantUpdateAddress restaurantPayload = objectMapper.readValue(payload, JmsRestaurantUpdateAddress.class);
            Restaurant existingRestaurant = restaurantService.get(restaurantPayload.getId());
            existingRestaurant.setAddress(restaurantPayload.getNewAddress());
            restaurantService.update(restaurantPayload.getId(), existingRestaurant);
        } catch (ResourceIsNotFoundException e){
            System.out.println("ResourceIsNotFoundException : " + e.getMessage());
        }
        catch (JsonProcessingException e) {
            System.out.println("message is not JSON. Details: " + e.getMessage());
        }


        System.out.println("Message from JMS " + payload);
        // listen
    }


    @JmsListener(destination = "another-queue") //
    public void listDraftMessage(Message<String> message) {
        String payload = message.getPayload();
        System.out.println("Message from JMS " + payload);
        // listen
    }
}

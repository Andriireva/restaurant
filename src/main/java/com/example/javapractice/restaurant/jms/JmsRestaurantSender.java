package com.example.javapractice.restaurant.jms;


import com.example.javapractice.restaurant.domain.Restaurant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsRestaurantSender {

    private static final String RESTAURANT_NOTIFIER_ADD = "restaurant-notifier-add";
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public JmsRestaurantSender(JmsTemplate jmsTemplate,
                               ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendRestaurantAddedMessage(Restaurant addedRestaurant) {
        // 1 arg - queue name
        // 2 arg - actual message
        try {
            jmsTemplate.convertAndSend(RESTAURANT_NOTIFIER_ADD, objectMapper.writeValueAsString(addedRestaurant));
        } catch (JsonProcessingException e) {
            System.out.println("Something went wrong during converting");
        }
    }
}

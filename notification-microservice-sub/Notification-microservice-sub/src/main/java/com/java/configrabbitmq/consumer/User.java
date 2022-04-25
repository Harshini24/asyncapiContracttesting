package com.java.configrabbitmq.consumer;

import com.java.configrabbitmq.config.MessagingQueue;
import com.java.configrabbitmq.dto.Order;
import com.java.configrabbitmq.dto.OrderStatus;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingQueue.queue)
    public void consumeMessageFromQueue(OrderStatus orderObj) {
        System.out.println("Message recieved from queue In Notification Microservice from Order Microsevice : " + orderObj);
    }
}
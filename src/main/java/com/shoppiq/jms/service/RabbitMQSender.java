package com.shoppiq.jms.service;

import com.shoppiq.entity.*;
import com.shoppiq.jms.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${javainuse.rabbitmq.exchange}")
    private String exchange;

    @Value("${javainuse.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Employee company) {
        rabbitTemplate.convertAndSend(exchange, routingkey, company);
        System.out.println("Send msg = " + company);

    }

    public void send(Item item) {
        rabbitTemplate.convertAndSend(exchange, routingkey, item);
        System.out.println("Sending message");
    }

    public void send(User user) {
        rabbitTemplate.convertAndSend(exchange, routingkey, user);
        System.out.println("Sending message");
    }

    public void send(Address address) {
        rabbitTemplate.convertAndSend(exchange, routingkey, address);
        System.out.println("Sending message");
    }

    public void send(Orders orders) {
        rabbitTemplate.convertAndSend(exchange, routingkey, orders);
        System.out.println("Sending message");
    }

    public void send(OrderDetails orderDetails) {
        rabbitTemplate.convertAndSend(exchange, routingkey, orderDetails);
        System.out.println("Sending message");
    }

    public void send(AuthGroup authGroup) {
        rabbitTemplate.convertAndSend(exchange, routingkey, authGroup);
        System.out.println("Sending message");
    }

}

package com.example.rabbitmq.two.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.stereotype.Service;

@Service
public class StreamHelloProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitStreamTemplate rabbitStreamTemplate;

    // Since there may be multiple RabbitStreamTemplates in the project, we specify which Bean to use.

    @Autowired
    public StreamHelloProducer(@Qualifier("rabbitStreamTemplateHello") RabbitStreamTemplate rabbitStreamTemplate,
                               RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitStreamTemplate = rabbitStreamTemplate;
    }

    // Directly send to Stream (suitable for fast and real-time processing)
    public void sendHello(String message){
        rabbitStreamTemplate.convertAndSend(message);
    }

    // Send messages via Exchange (suitable for broadcasting messages to multiple destinations)
    public void sendHelloUsingExchange(String message){
        rabbitTemplate.convertAndSend("x.hello", "rk.hello", message);
    }

}

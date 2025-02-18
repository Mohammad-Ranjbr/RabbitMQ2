package com.example.rabbitmq.two.producer;

import com.example.rabbitmq.two.model.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public DummyProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(DummyMessage dummyMessage){
        rabbitTemplate.convertAndSend("x.dummy", "", dummyMessage);
    }


}

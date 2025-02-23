package com.example.rabbitmq.two.producer;

import com.example.rabbitmq.two.model.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleActiveProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SingleActiveProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendDummy(){
        for(int i=0 ; i<10_000 ; i++){
            DummyMessage dummyMessage = new DummyMessage("Dummy " + i, i);
            rabbitTemplate.convertAndSend("x.single", "", dummyMessage);
        }
    }

}

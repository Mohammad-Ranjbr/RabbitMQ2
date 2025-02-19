package com.example.rabbitmq.two.producer;

import com.example.rabbitmq.two.model.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiplePrefetchProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MultiplePrefetchProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void simulateTransaction(){
        for(int i = 0; i < 20_000 ; i++){
            DummyMessage dummyMessage = new DummyMessage("Transaction " + i, 1);
            rabbitTemplate.convertAndSend("x.transaction", "", dummyMessage);
        }
    }

    public void simulateScheduler(){
        for(int i = 0; i < 200 ; i++){
            DummyMessage dummyMessage = new DummyMessage("Scheduler " + i, 1);
            rabbitTemplate.convertAndSend("x.scheduler", "", dummyMessage);
        }
    }

}

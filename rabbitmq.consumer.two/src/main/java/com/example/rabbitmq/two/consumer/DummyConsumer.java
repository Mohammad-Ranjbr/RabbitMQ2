package com.example.rabbitmq.two.consumer;

import com.example.rabbitmq.two.model.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class DummyConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DummyConsumer.class);

    @RabbitListener(queues = "q.dummy")
    public void listenDummy(DummyMessage dummyMessage){
        logger.info("Dummy Message : {}", dummyMessage);
    }

}

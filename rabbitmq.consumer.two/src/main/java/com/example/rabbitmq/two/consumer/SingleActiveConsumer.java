package com.example.rabbitmq.two.consumer;

import com.example.rabbitmq.two.model.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SingleActiveConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SingleActiveConsumer.class);

    @RabbitListener(queues = "q.single", concurrency = "5")
    public void listenDummy(DummyMessage dummyMessage) throws InterruptedException {
        logger.info("Consuming dummy message : {}", dummyMessage);
        TimeUnit.SECONDS.sleep(1);
    }

}

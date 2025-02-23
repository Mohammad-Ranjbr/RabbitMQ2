package com.example.rabbitmq.two.consumer;

import com.example.rabbitmq.two.model.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class SingleActiveConsumer {

    // What is the general idea?
    // All messages with a given routing key always go to a specific queue.
    // Only one active consumer consumes from this queue.
    // If the first consumer fails, the second consumer automatically takes over.
    // Implementation Steps
    // Creating an Exchange and Queue
    // Create an Exchange named x.single.
    // Create a Queue named q.single.
    // When creating the queue, enable the Single Active Consumer property.
    // This setting will cause only one active consumer to receive data from this queue.

    private static final Logger logger = LoggerFactory.getLogger(SingleActiveConsumer.class);

    @RabbitListener(queues = "q.single", concurrency = "5")
    public void listenDummy(DummyMessage dummyMessage) throws InterruptedException {
        logger.info("Consuming dummy message : {}", dummyMessage);
        TimeUnit.SECONDS.sleep(1);
    }

}

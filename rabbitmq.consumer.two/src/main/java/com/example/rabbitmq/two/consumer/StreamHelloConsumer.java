package com.example.rabbitmq.two.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class StreamHelloConsumer {

    private static final Logger logger = LoggerFactory.getLogger(StreamHelloConsumer.class);

    // The problem is that RabbitMQ Stream uses a "non-destructive consumption" method.
    // This means that when a Consumer connects to a Stream, it only receives messages that were published after it was started.
    // The old messages still exist in the Stream, but the Consumer does not read them.

    @RabbitListener(queues = "s.hello")
    public void listenHello(String message){
        logger.info("Consuming from stream: {}", message);
    }

}

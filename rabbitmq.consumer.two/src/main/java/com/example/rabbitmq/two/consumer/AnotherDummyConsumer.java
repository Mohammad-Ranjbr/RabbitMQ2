package com.example.rabbitmq.two.consumer;

import com.example.rabbitmq.two.model.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class AnotherDummyConsumer {

    private static final Logger logger = LoggerFactory.getLogger(AnotherDummyConsumer.class);

    // Instead of defining Queue and Exchange in a Configuration class (like we did in the Producer), we can do this directly inside the Listener (message consumer).

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "q.invoice", durable = "true"),
            exchange = @Exchange(name = "x.invoice", type = ExchangeTypes.FANOUT, durable = "true"),
            //key = "routing-key",
            ignoreDeclarationExceptions = "true"
    ))
    public void listenDummy(DummyMessage dummyMessage){
        logger.info("Dummy Message : {}", dummyMessage);
    }

}

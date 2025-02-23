package com.example.rabbitmq.two.producer;

import com.example.rabbitmq.two.model.DummyMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReliableProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ReliableProducer.class);

    @Autowired
    public ReliableProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    // In Spring, when a Bean (service class) is initialized, it means:
    // Spring has created an object of the class.
    // The dependencies (@Autowired) have been initialized.
    // But the methods of this class have not been executed yet!
    // The @PostConstruct methods are executed immediately after initialization and allow us to:
    // Do some initial settings.
    // Initialize some values.
    // Perform operations that need to be performed once at the beginning of the application execution.

    //@PostConstruct
    private void registerCallback(){
        // Checks whether the message arrived at the valid Exchange or not.
        rabbitTemplate.setConfirmCallback(((correlationData, ack, reason) -> {
            if(ack){
                logger.info("Message with correlation {} is published", correlationData.getId());
            } else {
                logger.warn("Invalid exchange , Message with correlation {} is not published", correlationData.getId());
            }

            // Checks whether the message has been routed to the Queue or not.
            rabbitTemplate.setReturnsCallback(returnedMessage -> {
                logger.info("Return callback");
                // If the message is not routed to any queue (NO_ROUTE), the routingKey is incorrect.
                if(returnedMessage.getReplyText() != null && returnedMessage.getReplyText().equalsIgnoreCase("NO_ROUTE")) {
                    String id = returnedMessage.getMessage().getMessageProperties().getHeader("spring_returned_message_correlation").toString();
                    logger.warn("Invalid routing key for id : {}", id);
                }
            });
        }));
    }

    public void sendDummyWithInvalidRoutingKey(DummyMessage dummyMessage){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("x.dummy", "invalidRoutingKey", dummyMessage, correlationData);
    }

    public void sendDummyToInvalidExchange(DummyMessage dummyMessage){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("invalidExchange", "", dummyMessage, correlationData);
    }

}

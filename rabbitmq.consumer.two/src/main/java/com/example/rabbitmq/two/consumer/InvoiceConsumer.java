package com.example.rabbitmq.two.consumer;

import com.example.rabbitmq.two.model.InvoiceCancelledMessage;
import com.example.rabbitmq.two.model.InvoiceCreatedMessage;
import com.example.rabbitmq.two.model.InvoicePaidMessage;
import com.example.rabbitmq.two.model.PaymentCancelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

//@Service
@RabbitListener(queues = "q.invoice")
public class InvoiceConsumer {

    // @RabbitListener on the class → specifies that this class receives messages from a specific queue.
    // @RabbitHandler on the methods → causes Spring to send the message to the appropriate method based on its type:
    // If the message is of type InvoiceCreatedMessage → the handleInvoiceCreated method is executed.
    // If the message is of type InvoicePaidMessage → the handleInvoicePaid method is executed.

    private static final Logger logger = LoggerFactory.getLogger(InvoiceConsumer.class);

    @RabbitHandler
    public void handleInvoiceCreated(InvoiceCreatedMessage invoiceCreatedMessage){
        logger.info("Invoice created : {}", invoiceCreatedMessage);
    }

    @RabbitHandler
    public void handleInvoicePaid(InvoicePaidMessage invoicePaidMessage){
        logger.info("Invoice paid : {}", invoicePaidMessage);
    }

    // So far we have learned that we can process different messages in a class. But what happens if we receive a message for which no method is defined?
    // Spring will throw an error!
    // But we can define a default method for these messages!
    // @RabbitHandler(isDefault = true) → Any message whose type is not specified is sent to this method.
    // The input of the method is Object → That is, it takes any type of message and displays it in the log.

    @RabbitHandler(isDefault = true)
    public void handleDefault(Object message){
        logger.warn("Received Unknown Message : {}", message);
    }

    // Each consumer can be a producer itself
    // The queue.invoice consumer sends the result to queue.invoice.cancel after processing
    // The queue.invoice.cancel queue stores this message for other consumers to process

    @RabbitHandler
    @SendTo("x.invoice.cancel/")
    public PaymentCancelStatus handleInvoiceCancelled(InvoiceCancelledMessage invoiceCancelledMessage){
        var randomStatus = ThreadLocalRandom.current().nextBoolean();
        return new PaymentCancelStatus(randomStatus, LocalDate.now(), invoiceCancelledMessage.getInvoiceNumber());
    }

}

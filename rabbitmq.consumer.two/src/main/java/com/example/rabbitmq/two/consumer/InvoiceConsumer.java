package com.example.rabbitmq.two.consumer;

import com.example.rabbitmq.two.model.InvoiceCreatedMessage;
import com.example.rabbitmq.two.model.InvoicePaidMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "q.invoice")
public class InvoiceConsumer {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceConsumer.class);

    @RabbitHandler
    public void handleInvoiceCreated(InvoiceCreatedMessage invoiceCreatedMessage){
        logger.info("Invoice created : {}", invoiceCreatedMessage);
    }

    @RabbitHandler
    public void handleInvoicePaid(InvoicePaidMessage invoicePaidMessage){
        logger.info("Invoice paid : {}", invoicePaidMessage);
    }

}

package com.example.rabbitmq.two.producer;

import com.example.rabbitmq.two.model.InvoiceCancelledMessage;
import com.example.rabbitmq.two.model.InvoiceCreatedMessage;
import com.example.rabbitmq.two.model.InvoicePaidMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "x.invoice";

    @Autowired
    public InvoiceProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendInvoiceCreated(InvoiceCreatedMessage invoiceCreatedMessage){
        rabbitTemplate.convertAndSend(EXCHANGE, "", invoiceCreatedMessage);
    }

    public void sendInvoicePaid(InvoicePaidMessage invoicePaidMessage){
        rabbitTemplate.convertAndSend(EXCHANGE, "", invoicePaidMessage);
    }

    public void sendInvoiceCancelled(InvoiceCancelledMessage invoiceCancelledMessage){
        rabbitTemplate.convertAndSend(EXCHANGE, "", invoiceCancelledMessage);
    }

}

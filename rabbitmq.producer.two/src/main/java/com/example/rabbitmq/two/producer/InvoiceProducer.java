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
    public InvoiceProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Now we modify InvoiceProducer to send messages with the invoice number as the Routing Key.
    // Consistent Hash Exchange ensures that all messages related to an invoice go to the same queue.
    // RabbitMQ uses mathematical approximations and internal algorithms for distribution when distributing messages,
    // so there may be slight differences, but the overall trend is as expected.

    public void sendInvoiceCreated(InvoiceCreatedMessage invoiceCreatedMessage) {
        rabbitTemplate.convertAndSend(EXCHANGE, invoiceCreatedMessage.getInvoiceNumber(), invoiceCreatedMessage);
    }

    public void sendInvoicePaid(InvoicePaidMessage invoicePaidMessage) {
        rabbitTemplate.convertAndSend(EXCHANGE, invoicePaidMessage.getInvoiceNumber(), invoicePaidMessage);
    }

    public void sendInvoiceCancelled(InvoiceCancelledMessage invoiceCancelledMessage) {
        rabbitTemplate.convertAndSend(EXCHANGE, invoiceCancelledMessage.getInvoiceNumber(), invoiceCancelledMessage);
    }

}

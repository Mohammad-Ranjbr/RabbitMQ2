package com.example.rabbitmq.two;

import com.example.rabbitmq.two.model.DummyMessage;
import com.example.rabbitmq.two.model.InvoiceCancelledMessage;
import com.example.rabbitmq.two.model.InvoiceCreatedMessage;
import com.example.rabbitmq.two.model.InvoicePaidMessage;
import com.example.rabbitmq.two.producer.DummyProducer;
import com.example.rabbitmq.two.producer.InvoiceProducer;
import com.example.rabbitmq.two.producer.MultiplePrefetchProducer;
import com.example.rabbitmq.two.producer.SingleActiveProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final DummyProducer dummyProducer;
	private final MultiplePrefetchProducer multiplePrefetchProducer;
	private final InvoiceProducer invoiceProducer;
	private final SingleActiveProducer singleActiveProducer;

	@Override
	public void run(String... args) throws Exception {
//		DummyMessage dummyMessage = new DummyMessage("Content", 1);
//		dummyProducer.sendMessage(dummyMessage);

		// The number 10_000 is the same as ten thousand (10,000), but written with an underscore (_).
		// The underscore (_) has no effect on the value of the number and is only used for readability.
//		for(int i=0 ; i<10_000 ; i++){
//			DummyMessage dummyMessage = new DummyMessage("Content " + i, 1);
//			dummyProducer.sendMessage(dummyMessage);
//			TimeUnit.SECONDS.sleep(1);
//		}

//		for(int i=0 ; i<500 ; i++){
//			DummyMessage dummyMessage = new DummyMessage("Content " + i, 1);
//			dummyProducer.sendMessage(dummyMessage);
//		}

//		multiplePrefetchProducer.simulateTransaction();
//		multiplePrefetchProducer.simulateScheduler();

//		String randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(100, 200);
//		InvoiceCreatedMessage invoiceCreatedMessage = new InvoiceCreatedMessage(155.75, LocalDate.now(), "USD", randomInvoiceNumber);
//		invoiceProducer.sendInvoiceCreated(invoiceCreatedMessage);
//
//		randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(200, 300);
//		String randomPaymentNumber = "PAY-" + ThreadLocalRandom.current().nextInt(1000, 2000);
//		InvoicePaidMessage invoicePaidMessage = new InvoicePaidMessage(randomInvoiceNumber, LocalDate.now(), randomPaymentNumber);
//		invoiceProducer.sendInvoicePaid(invoicePaidMessage);
//
//		randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(300, 400);
//		InvoiceCancelledMessage invoiceCancelledMessage = new InvoiceCancelledMessage("Invoice Cancelled", randomInvoiceNumber, LocalDate.now());
//		invoiceProducer.sendInvoiceCancelled(invoiceCancelledMessage);

//		for(int i=0 ; i<200 ; i++){
//			String invoiceNumber = "INV-" + (i % 60);
//			InvoiceCreatedMessage invoiceCreatedMessage = new InvoiceCreatedMessage(ThreadLocalRandom.current().nextInt(1, 200), LocalDate.now(), "USD", invoiceNumber);
//			invoiceProducer.sendInvoiceCreated(invoiceCreatedMessage);
//		}

		singleActiveProducer.sendDummy();

	}

}

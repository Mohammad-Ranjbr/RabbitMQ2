package com.example.rabbitmq.two;

import com.example.rabbitmq.two.model.DummyMessage;
import com.example.rabbitmq.two.producer.DummyProducer;
import com.example.rabbitmq.two.producer.MultiplePrefetchProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final DummyProducer dummyProducer;
	private final MultiplePrefetchProducer multiplePrefetchProducer;

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

		multiplePrefetchProducer.simulateTransaction();
		multiplePrefetchProducer.simulateScheduler();

	}

}

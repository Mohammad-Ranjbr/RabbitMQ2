package com.example.rabbitmq.two;

import com.example.rabbitmq.two.model.DummyMessage;
import com.example.rabbitmq.two.producer.DummyProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final DummyProducer dummyProducer;

	@Override
	public void run(String... args) throws Exception {
		DummyMessage dummyMessage = new DummyMessage("Content", 1);
		dummyProducer.sendMessage(dummyMessage);
	}

}

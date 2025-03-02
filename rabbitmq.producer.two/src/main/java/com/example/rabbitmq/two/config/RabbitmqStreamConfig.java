package com.example.rabbitmq.two.config;

import com.rabbitmq.stream.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

@Configuration
public class RabbitmqStreamConfig {

    // If we want to have one of the types, we need to specify which one we want.
    // Here a Qualifier is defined so that we can specifically use rabbitStreamTemplateHello when using it.

    @Bean
    @Qualifier("rabbitStreamTemplateHello")
    public RabbitStreamTemplate rabbitStreamTemplateHello(Environment environment) {
        return new RabbitStreamTemplate(environment, "s.hello");
    }

}

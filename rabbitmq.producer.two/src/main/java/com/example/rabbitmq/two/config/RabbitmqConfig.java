package com.example.rabbitmq.two.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    // Jackson2JsonMessageConverter is a RabbitMQ-specific Message Converter that converts messages to JSON and from JSON to Objects.
    // Instead of manually converting ObjectMapper.writeValueAsString(object), this is done automatically.
    // Here, we pass the ObjectMapper we created above to Jackson2JsonMessageConverter so that it has the required settings.
    // Spring Boot automatically uses these settings, so there is no need to manually convert messages to JSON.

    // When we use Jackson2JsonMessageConverter, Spring Boot adds a header called typeId to RabbitMQ messages behind the scenes. This header contains the full class name (including package).
    // What is the problem?
    // If the message class (DummyMessage) is in different packages in the Producer and Consumer, Spring cannot deserialize the message correctly.
    // Because the typeId stored in the Producer does not match the typeId expected in the Consumer.
    // Solution: Unify package names in Producer and Consumer

    @Bean
    public ObjectMapper getObjectMapper(){
        return JsonMapper.builder().findAndAddModules().build();
    }

    @Bean
    public Jackson2JsonMessageConverter getJackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter(getObjectMapper());
    }

}

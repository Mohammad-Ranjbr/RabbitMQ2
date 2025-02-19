package com.example.rabbitmq.two.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public ObjectMapper getObjectMapper(){
        return JsonMapper.builder().findAndAddModules().build();
    }

    @Bean
    public Jackson2JsonMessageConverter getJackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter(getObjectMapper());
    }

    // A Container Factory is used for @RabbitListener. Here, we have set the prefetch value to 1 to distribute messages evenly across consumers
    // This method returns a Container Factory of type SimpleMessageListenerContainer which is used to control the behavior of consumers in RabbitMQ.
    // SimpleRabbitListenerContainerFactoryConfigurer: A Spring Boot helper class that performs the default configuration for RabbitListenerContainerFactory.
    // ConnectionFactory: This object is responsible for creating and managing a connection to RabbitMQ.

    @Bean
    RabbitListenerContainerFactory<SimpleMessageListenerContainer> prefetchOneContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer simpleRabbitListenerContainerFactoryConfigurer,
            ConnectionFactory connectionFactory){
        var factory = new SimpleRabbitListenerContainerFactory();
        // // It is important that this line is executed before setPrefetchCount(1), because Spring settings should not overwrite the prefetch value.
        simpleRabbitListenerContainerFactoryConfigurer.configure(factory, connectionFactory);
        // This means that each consumer processes only one message and receives the next message only when the previous
        // message has been acknowledged. This ensures that messages are distributed evenly across consumers, especially for consumers with slow processing.
        // First configure(factory, connectionFactory) is executed to apply all the default Spring settings.
        // Next setPrefetchCount(1) is executed, which overrides the default value of prefetch = 250 and replaces it with a value of 1.
        factory.setPrefetchCount(1);
        return factory;
    }

}

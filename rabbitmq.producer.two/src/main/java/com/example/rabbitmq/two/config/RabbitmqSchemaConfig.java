package com.example.rabbitmq.two.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqSchemaConfig {

    //@Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("x.invoice", true, false, null);
    }

    // If exclusive = true:
    //This queue will only be available to the same connection that created it.
    //The queue will be automatically deleted if that connection is closed.
    //@Bean
    public Queue queue() {
        return new Queue("q.invoice", true, false, false);
    }

    //@Bean
    public Binding binding() {
        //return new Binding("q.invoice", Binding.DestinationType.QUEUE, "x.invoice", "", null);
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    @Bean
    public Declarables createRabbitmqSchema() {
        return new Declarables(new FanoutExchange("x.invoice", true, false, null), new Queue("q.invoice"), new Binding("q.invoice", Binding.DestinationType.QUEUE, "x.invoice", "", null));
    }

}

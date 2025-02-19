package com.example.rabbitmq.two.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class RabbitmqScheduler {

    private final RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;
    private static final Logger logger = LoggerFactory.getLogger(RabbitmqScheduler.class);

    // Problem: Temporarily shutdown a consumer without stopping RabbitMQ
    // In an organization, RabbitMQ is used to create an accounting general ledger.
    // But from 11pm to midnight, no transactions should be recorded in the general ledger.
    // Wrong approach:
    // One way is to stop the source system (e.g., payment gateway) and not allow transactions to be recorded.
    // But this will disrupt other services and make customers unhappy.
    // Better solution:
    // The payment system will continue to work and send messages to RabbitMQ,
    // but during this time, we will only shut down the consumer related to the general ledger.
    // How to shut down and restart the consumer?
    // Instead of manually turning the service off and on every day,
    // we can do this automatically using Spring Scheduler.

    // This is wrong, because RabbitListenerEndpointRegistry is a built-in Bean in Spring Boot and you don't need to define it manually.
    // If you get the issue No beans of 'RabbitListenerEndpointRegistry' type found, it means that this Bean is not initialized properly.
    // Usually RabbitListenerEndpointRegistry is initialized directly via @Autowired. But here, its value is dynamically obtained from ApplicationContext
    @Autowired
    public RabbitmqScheduler(ApplicationContext context){
        this.rabbitListenerEndpointRegistry = context.getBean(RabbitListenerEndpointRegistry.class);
    }

    // Stops all @RabbitListeners.
    @Scheduled(cron = "0 22 10 * * *") // second minute hour day month weekday
    public void stopAll(){
         rabbitListenerEndpointRegistry.getListenerContainers().forEach(c -> {
             logger.info("Stopping container {}", c);
             c.stop();
         });
    }

    // Enables all @RabbitListeners.
    @Scheduled(cron = "0 23 10 * * *")
    public void startAll(){
        rabbitListenerEndpointRegistry.getListenerContainers().forEach(c -> {
            logger.info("Starting container {}", c);
            c.start();
        });
    }

}

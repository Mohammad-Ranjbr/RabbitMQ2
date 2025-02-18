package com.example.rabbitmq.two.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class RabbitmqScheduler {

    @Autowired
    private  RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;
    private static final Logger logger = LoggerFactory.getLogger(RabbitmqScheduler.class);

    @Scheduled(cron = "0 0 23 * * *")
    public void stopAll(){
         rabbitListenerEndpointRegistry.getListenerContainers().forEach(c -> {
             logger.info("Stopping container {}", c);
             c.stop();
         });
    }

    @Scheduled(cron = "1 0 0 * * *")
    public void startAll(){
        rabbitListenerEndpointRegistry.getListenerContainers().forEach(c -> {
            logger.info("Starting container {}", c);
            c.start();
        });
    }

}

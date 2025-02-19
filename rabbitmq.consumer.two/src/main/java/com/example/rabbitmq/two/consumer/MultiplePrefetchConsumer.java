package com.example.rabbitmq.two.consumer;

import com.example.rabbitmq.two.model.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MultiplePrefetchConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MultiplePrefetchConsumer.class);

    @RabbitListener(queues = "q.transaction", concurrency = "2")
    public void listenTransaction(DummyMessage dummyMessage) throws InterruptedException {
        logger.info("Consuming transaction : {}", dummyMessage.getContent());
        TimeUnit.SECONDS.sleep(1);
    }

    @RabbitListener(queues = "q.scheduler", concurrency = "2", containerFactory = "prefetchOneContainerFactory")
    public void listenScheduler(DummyMessage dummyMessage) throws InterruptedException {
        logger.info("Consuming scheduler : {}", dummyMessage.getContent());
        TimeUnit.SECONDS.sleep(60);
    }

    // Transactional Exchange (such as invoice payments) that need to be processed in real-time and their processing speed is fast (e.g. 100 milliseconds).
    // Scheduled Exchange (such as general accounting) that needs to be processed slower and takes, for example, a minute.
    // For these two types of tasks, we need to have different prefetch settings:
    // For transactions, we use the default prefetch value of 250 because the processing speed is high.
    // For scheduled, we use a prefetch value of 1 because the processing takes more time and the messages need to be distributed evenly.
    // Challenge:
    // If we want to have different prefetch settings for each of these consumers, we need to use a way to set a different prefetch value for each queue.
    // The problem is that the prefetch settings in application.yaml are the same for all consumers by default.
    // Solution:
    // Using RabbitListener:
    // We can use different container factories for each listener method we want to listen to the queues.
    // For transactions, we use the default Spring container factory with a prefetch value of 250.
    // For scheduled, we create a new container factory with a prefetch value of 1.

}

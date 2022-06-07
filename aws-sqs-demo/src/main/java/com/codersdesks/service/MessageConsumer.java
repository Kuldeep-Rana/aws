package com.codersdesks.service;

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private Logger logger = LoggerFactory.getLogger(MessageService.class);

    @SqsListener(value = "orders",deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consumeOrder(String message){
        logger.info("message received from sqs {}", message);

    }
}

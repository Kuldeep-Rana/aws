package com.codersdesks.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.codersdesks.config.ApplicationProperties;
import com.codersdesks.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private AmazonSQS sqs;

    public void send(String message){

        SendMessageRequest request = new SendMessageRequest(applicationProperties.getQueueUrl(), message);
        SendMessageResult result = sqs.sendMessage(request);
        logger.info(result.toString());
    }

    public void sendOrder(Order order){
        try {
            String message = new ObjectMapper().writeValueAsString(order);
            SendMessageRequest request = new SendMessageRequest(applicationProperties.getQueueUrl(), message);
            SendMessageResult result = sqs.sendMessage(request);
            logger.info("order sent successfully " +result.toString());
        }catch (Exception e){
            logger.error("error in send order ",e);
        }
    }
}

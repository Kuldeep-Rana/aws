package com.codersdesks.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsQueueConfig {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    protected AWSCredentialsProvider credentials;

    /**
     * By Default, AmazonSQS will be available automatically using spring boot auto config based on the
     * configuration provided in the application.properties or application.yaml file
     *
     * Enables this bean if you want to create Amazon sqs object manually.
     * Also, you have to exclude SqsAutoConfiguration class in your SpringBoot config
     */
    //@Bean
    public AmazonSQS getAmazonSQS() {
        return AmazonSQSClientBuilder.standard()
                .withCredentials(credentials)
                .withRegion(applicationProperties.getRegion())
                .build();
    }


}

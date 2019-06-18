package com.jjdev.sqs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jgilson
 */
@RestController
@RequestMapping("api/v1/queue")
public class JSQSController {

    @Value("${cloud.aws.end-point.uri}")
    private String sqsEndpoint;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    private static final Logger log = LoggerFactory.getLogger(JSQSController.class);

    //--------------------------------------------------------------------------
    @PostMapping(value = "/sendMessage")
    public void sendMessage(@RequestParam String message) {
        queueMessagingTemplate.send(sqsEndpoint, MessageBuilder.withPayload(message).build());
    }

    @SqsListener("spring-boot-sqs")
    public void getMessage(String message) {
        log.info("Message from SQS queue - {}", message);
    }

}

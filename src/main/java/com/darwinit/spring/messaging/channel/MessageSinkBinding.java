package com.darwinit.spring.messaging.channel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.Router;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(MessageSink.class)
public class MessageSinkBinding {
    private static Logger logger = LogManager.getLogger(MessageSinkBinding.class);

    @Router(inputChannel = "input_1", defaultOutputChannel = "aggregator")
    public String receiveMessage1(GenericMessage<?> message) {
        logger.debug("router receive {}", message);
        return "aggregator";
    }

    @Router(inputChannel = "input_2", defaultOutputChannel = "aggregator")
    public String receiveMessage2(GenericMessage<?> message) {
        logger.debug("router receive {}", message);
        return "aggregator";
    }
}
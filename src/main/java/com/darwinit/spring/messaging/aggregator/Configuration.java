package com.darwinit.spring.messaging.aggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.integration.aggregator.AggregatingMessageHandler;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mongodb.store.MongoDbMessageStore;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    MongoDbFactory mongoDbFactory;

    @Autowired
    MongoDbMessageStore messageStore;

    @Bean
    @ServiceActivator(inputChannel = "aggregator", outputChannel = "output")
    public AggregatingMessageHandler aggregatingMessageHandler() {
        AggregatingMessageHandler handler = new AggregatingMessageHandler(
                new MessageGroupProcessor(),
                messageStore,
                new CorrelationStrategy(),
                new ReleaseStrategy());
        handler.setOutputChannelName("output");
        return handler;
    }

    @Bean
    MongoDbMessageStore messageStore() {
        return new MongoDbMessageStore(mongoDbFactory);
    }

}

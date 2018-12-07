package com.darwinit.spring.messaging.aggregator;

import com.darwinit.spring.messaging.channel.MessageSink;
import com.darwinit.spring.messaging.domain.Bar;
import com.darwinit.spring.messaging.domain.Foo;
import com.darwinit.spring.messaging.domain.FooBar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.store.MessageGroup;

import java.util.List;
import java.util.stream.Collectors;

public class MessageGroupProcessor implements org.springframework.integration.aggregator.MessageGroupProcessor {
    private static Logger logger = LogManager.getLogger(MessageGroupProcessor.class);

    @Autowired
    private MessageSink messageSink;

    @Override
    public Object processMessageGroup(MessageGroup group) {
        logger.debug("process message group");
        List<Foo> foos=group.getMessages().stream()
                .filter(message -> message.getPayload() instanceof Foo)
                .map(message -> (Foo) message.getPayload()).collect(Collectors.toList());
        List<Bar> bars=group.getMessages().stream()
                .filter(message -> message.getPayload() instanceof Bar)
                .map(message -> (Bar) message.getPayload()).collect(Collectors.toList());

        return new FooBar(foos, bars);
    }
}

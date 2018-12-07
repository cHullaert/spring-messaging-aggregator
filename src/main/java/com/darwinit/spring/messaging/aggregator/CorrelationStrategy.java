package com.darwinit.spring.messaging.aggregator;

import com.darwinit.spring.messaging.domain.Bar;
import com.darwinit.spring.messaging.domain.Foo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.Message;

public class CorrelationStrategy implements org.springframework.integration.aggregator.CorrelationStrategy {
    private static Logger logger = LogManager.getLogger(CorrelationStrategy.class);

    @Override
    public Object getCorrelationKey(Message<?> message) {
        logger.debug("get correlation key");
        if(message.getPayload() instanceof Foo) {
            Foo foo= (Foo) message.getPayload();
            return foo.getKey();
        }
        else {
            Bar bar=(Bar) message.getPayload();
            return bar.getKey();
        }
    }
}

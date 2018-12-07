package com.darwinit.spring.messaging.aggregator;

import com.darwinit.spring.messaging.domain.Bar;
import com.darwinit.spring.messaging.domain.Foo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.store.MessageGroup;

public class ReleaseStrategy implements org.springframework.integration.aggregator.ReleaseStrategy {
    private static Logger logger = LogManager.getLogger(ReleaseStrategy.class);

    @Override
    public boolean canRelease(MessageGroup group) {
        logger.debug("resolve strategy");
        return group.getMessages().stream().filter(message -> message.getPayload() instanceof Bar).count()
                    +group.getMessages().stream().filter(message -> message.getPayload() instanceof Foo).count()==4;
    }
}

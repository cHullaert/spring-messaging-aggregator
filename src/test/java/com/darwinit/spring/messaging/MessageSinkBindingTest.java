package com.darwinit.spring.messaging;

import com.darwinit.spring.messaging.aggregator.ServiceApplication;
import com.darwinit.spring.messaging.channel.MessageSink;
import com.darwinit.spring.messaging.channel.MessageSinkBinding;
import com.darwinit.spring.messaging.domain.Bar;
import com.darwinit.spring.messaging.domain.Foo;
import com.darwinit.spring.messaging.domain.FooBar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ServiceApplication.class, MessageSinkBinding.class} )
@ActiveProfiles(profiles = "test")
public class MessageSinkBindingTest {

    @Autowired
    private MessageSink messageSink;

    @Autowired
    MessageCollector messageCollector;

    @Test
    public void testAggregator() {
        GenericMessage<Foo> springMessage = new GenericMessage<>(new Foo("key_1", 1));
        messageSink.input_1().send(springMessage);
        springMessage = new GenericMessage<>(new Foo("key_2", 2));
        messageSink.input_1().send(springMessage);
        springMessage = new GenericMessage<>(new Foo("key_2", 3));
        messageSink.input_1().send(springMessage);
        springMessage = new GenericMessage<>(new Foo("key_1", 4));
        messageSink.input_1().send(springMessage);

        GenericMessage<Bar> springBarMessage = new GenericMessage<>(new Bar("key_1", 5));
        messageSink.input_2().send(springBarMessage);
        springBarMessage = new GenericMessage<>(new Bar("key_1", 6));
        messageSink.input_2().send(springBarMessage);

        Message<?> message=messageCollector.forChannel(messageSink.output()).poll();
        assertTrue(message.getPayload() instanceof FooBar);
        FooBar payload= (FooBar) message.getPayload();

        assertEquals(2, payload.getBars().size());
        assertEquals(2, payload.getFoos().size());

        Integer[] array={5, 6};
        assertArrayEquals(array, payload.getBars().stream().map(bar -> bar.getValue()).sorted().toArray());
        Integer[] array2={1, 4};
        assertArrayEquals(array2, payload.getFoos().stream().map(foo -> foo.getValue()).sorted().toArray());
    }
}

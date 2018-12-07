package com.darwinit.spring.messaging.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageSink {

    @Input("input_1")
    SubscribableChannel input_1();

    @Input("input_2")
    SubscribableChannel input_2();

    @Output("output")
    MessageChannel output();
}

package com.example.JavaLabSpringCloudAccount.source;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestChannelSource {
    @Output("TestChannel")
    MessageChannel testChannel();
}

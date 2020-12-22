package com.example.JavaLabSpringCloudWeb.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestChannelSource {
    @Output("testChannel")
    MessageChannel testChannel();
}

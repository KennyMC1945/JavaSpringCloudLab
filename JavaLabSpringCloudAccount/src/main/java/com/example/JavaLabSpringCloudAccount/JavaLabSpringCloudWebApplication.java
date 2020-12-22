package com.example.JavaLabSpringCloudAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@EnableDiscoveryClient
public class JavaLabSpringCloudWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaLabSpringCloudWebApplication.class, args);
	}



}

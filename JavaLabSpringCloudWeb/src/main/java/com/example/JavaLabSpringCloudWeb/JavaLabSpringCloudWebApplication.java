package com.example.JavaLabSpringCloudWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableFeignClients
public class JavaLabSpringCloudWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaLabSpringCloudWebApplication.class, args);
	}

}
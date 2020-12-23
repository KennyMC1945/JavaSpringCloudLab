package com.example.JavaLabSpringCloudWeb;

import com.example.JavaLabSpringCloudWeb.model.Store;
import com.example.JavaLabSpringCloudWeb.repository.FileRepository;
import com.example.JavaLabSpringCloudWeb.repository.IRepository;
import com.example.JavaLabSpringCloudWeb.service.CourseService;
import com.example.JavaLabSpringCloudWeb.service.FileCourseService;
import com.example.JavaLabSpringCloudWeb.service.MongoCourseService;
import com.example.JavaLabSpringCloudWeb.source.Sender;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Config {
    @Bean
    public CourseService getAccService() throws IOException{
        return new MongoCourseService();
    }
    @Bean
    public IRepository getRepository() {
        return new FileRepository();
    }

    @Bean
    public Store getStore() {
        return new Store(getRepository().read());
    }

    @Bean
    public Queue getMainQueue(){
        return new Queue("mainQueue");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("rpc-ex");
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }
}

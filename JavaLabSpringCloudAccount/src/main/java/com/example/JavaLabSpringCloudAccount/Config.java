package com.example.JavaLabSpringCloudAccount;

import com.example.JavaLabSpringCloudAccount.model.Store;
import com.example.JavaLabSpringCloudAccount.repository.FileRepository;
import com.example.JavaLabSpringCloudAccount.repository.IRepository;
import com.example.JavaLabSpringCloudAccount.service.AccountService;
import com.example.JavaLabSpringCloudAccount.service.FileAccountService;
import com.example.JavaLabSpringCloudAccount.service.MongoAccountService;
import com.example.JavaLabSpringCloudAccount.source.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;
import java.io.IOException;

@Configuration
public class Config {
    @Bean
    public AccountService getAccService() throws IOException{
        return new MongoAccountService();
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
    public Receiver getReceiver() {
        return new Receiver();
    }

    @Bean
    public Queue queue() {
        return new Queue("rpc-ex.requests");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("rpc-ex");
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("rpc");
    }
}

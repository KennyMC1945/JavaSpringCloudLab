package com.example.JavaLabSpringCloudAccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Document(collection = "accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    @Field("name")
    private String name;
    @Field("id")
    private int id;
    @Field("subscriptions")
    private List<Integer> subscriptions;
    @Field("code")
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Account(String name, int id) {
        this.name = name;
        this.id = id;
        this.subscriptions = new ArrayList<>();
        this.code = (new Random()).nextInt(8999)+1000;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account() {
    }

    public Account(String name, int id, List<Integer> subscriptions) {
        this.name = name;
        this.id = id;
        this.subscriptions = subscriptions;
        this.code = (new Random()).nextInt(8999)+1000;
    }

    public List<Integer> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Integer> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        String s = "User #"+id+": "+name+"\n";
        s += "Subscriptions : [";
        for (Integer id : subscriptions) {
            s += id + " ";
        }
        s += "]";
        return s;
    }
}

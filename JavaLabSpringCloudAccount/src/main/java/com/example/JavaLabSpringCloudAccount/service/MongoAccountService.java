package com.example.JavaLabSpringCloudAccount.service;

import com.example.JavaLabSpringCloudAccount.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

public class MongoAccountService implements AccountService{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void addSubscription(int accId, int courseId) {
        Account acc = getAccount(accId);
        List<Integer> subs = acc.getSubscriptions();
        subs.add(courseId);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(accId)), Update.update("subscriptions", subs), Account.class);
    }

    @Override
    public void removeSubscription(int accId, Integer courseId) {
        Account acc = getAccount(accId);
        List<Integer> subs = acc.getSubscriptions();
        subs.remove(courseId);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(accId)), Update.update("subscriptions", subs), Account.class);
    }

    @Override
    public void addAccount(Account acc) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "id"));
        query.limit(1);
        Account maxObject = mongoTemplate.findOne(query, Account.class);
        int id = maxObject.getId();
        acc.setId(id+1);
        acc.setSubscriptions(new ArrayList<>());
        mongoTemplate.insert(acc);
    }

    @Override
    public Account getAccount(int id) {
        Account acc = mongoTemplate.query(Account.class).matching(Query.query(Criteria.where("id").is(id))).oneValue();
        return acc;
    }
}

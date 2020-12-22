package com.example.JavaLabSpringCloudAccount.service;

import com.example.JavaLabSpringCloudAccount.model.Account;
import com.example.JavaLabSpringCloudAccount.model.Store;
import com.example.JavaLabSpringCloudAccount.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class FileAccountService implements AccountService {

    @Autowired
    public Store store;

    @Autowired
    public IRepository repository;

    @Override
    public void addSubscription(int accId, int courseId) {
        Account user = getAccount(accId);
        List<Integer> subscriptions = user.getSubscriptions();
        subscriptions.add(courseId);
        user.setSubscriptions(subscriptions);
        repository.write(store.accountList);

    }

    @Override
    public void removeSubscription(int accId, Integer courseId) {
        Account user = getAccount(accId);
        List<Integer> subscriptions = user.getSubscriptions();
        if (subscriptions.contains(courseId)) {
            subscriptions.remove(courseId);
            user.setSubscriptions(subscriptions);
            repository.write(store.accountList);
        }
    }

    @Override
    public void addAccount(Account acc) {
        int max_id = 0;
        for (Account account: store.accountList) {
            if (account.getId() > max_id) max_id = account.getId();
        }
        acc.setId(max_id+1);
        acc.setSubscriptions(new ArrayList<>());
        store.accountList.add(acc);
        repository.write(store.accountList);
    }

    @Override
    public Account getAccount(int id) {
        for (Account a: store.accountList){
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }





}
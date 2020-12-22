package com.example.JavaLabSpringCloudAccount.service;

import com.example.JavaLabSpringCloudAccount.model.Account;

public interface AccountService {
    void addSubscription(int accId, int courseId);
    void removeSubscription(int accId, Integer courseId);
    void addAccount(Account acc);
    Account getAccount(int id);
}

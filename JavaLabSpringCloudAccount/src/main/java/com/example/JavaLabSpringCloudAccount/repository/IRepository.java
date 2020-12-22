package com.example.JavaLabSpringCloudAccount.repository;

import com.example.JavaLabSpringCloudAccount.model.Account;

import java.util.List;

public interface IRepository {
    List<Account> read();
    void write(List<Account> accounts);
}

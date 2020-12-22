package com.example.JavaLabSpringCloudAccount.repository;

import com.example.JavaLabSpringCloudAccount.model.Account;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements IRepository {
    private static final String DB_FILENAME = "accounts.json";
    private static final String[] NAMES = {"A. Smith", "T. Armstrong", "A. Lee", "J. Edison",
            "T. Ericson", "M. Tetcher", "B. Stohalski", "S. Valeska", "J. Carry", "M. Watson"};
    private static final int DB_BASEUSERS = 10;

    public List<Account> createDBFile() throws IOException {
        File db = new File(DB_FILENAME);
        ObjectMapper mapper = new ObjectMapper();
        db.createNewFile();
        List<Account> accounts = new ArrayList<>();
        FileWriter fw = new FileWriter(db,false);
        for (int i = 0; i<DB_BASEUSERS; i++) {
            accounts.add(new Account(NAMES[i], i));
        }
        mapper.writeValue(fw, accounts);
        return accounts;
    }

    @Override
    public List<Account> read() {
        File db = new File(DB_FILENAME);
        try {
            if (!db.exists()) {
                return createDBFile();
            } else {
                ObjectMapper mapper = new ObjectMapper();
                List<Account> accounts = mapper.readValue(db, new TypeReference<ArrayList<Account>>() {});
                return accounts;
            }
        } catch (IOException e) {
            System.out.println("ERROR: Troubles with json file");
            return null;
        }

    }

    @Override
    public void write(List<Account> accounts) {
        try {
            File db = new File(DB_FILENAME);
            ObjectMapper mapper = new ObjectMapper();
            FileWriter fw = new FileWriter(db, false);
            mapper.writeValue(fw, accounts);
        } catch (IOException e) {
            System.out.println("ERROR: Writing to JSON");
        }
    }
}

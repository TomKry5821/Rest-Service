package com.krypczyk.restservice.storage;

import com.krypczyk.restservice.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserStorage {

    private static UserStorage instance;
    private long id = 1;
    private Map<Long, User> storage;

    private UserStorage() {
        this.storage = new HashMap<>();
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        this.save(user);

    }

    public static UserStorage getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserStorage();
        }
        return instance;
    }

    public Map<Long, User> getStorage() {
        return this.storage;
    }

    public void save(User user) {
        this.getStorage().put(id, user);
        this.id += 1;
    }

}

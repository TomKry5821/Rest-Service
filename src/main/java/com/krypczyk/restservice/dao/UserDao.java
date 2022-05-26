package com.krypczyk.restservice.dao;

import com.krypczyk.restservice.model.User;
import com.krypczyk.restservice.storage.UserStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao implements Dao<User> {


    @Override
    public List<User> getAll() {
        return new ArrayList<>(UserStorage.getInstance().getStorage().values());
    }

    @Override
    public void save(User user) {
        UserStorage.getInstance().save(user);
    }

}

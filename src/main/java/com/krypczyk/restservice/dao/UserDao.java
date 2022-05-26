package com.krypczyk.restservice.dao;

import com.krypczyk.restservice.model.User;
import com.krypczyk.restservice.storage.UserStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {


    public List<User> getAll() {
        return new ArrayList<>(UserStorage.getInstance().getStorage().values());
    }

    public void save(User user) {
        UserStorage.getInstance().save(user);
    }

}

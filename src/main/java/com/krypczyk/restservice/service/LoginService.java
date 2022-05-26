package com.krypczyk.restservice.service;

import com.krypczyk.restservice.Exception.UnauthorizedException;
import com.krypczyk.restservice.dao.UserDao;
import com.krypczyk.restservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorizationManager authorizationManager;

    public String login(String username, String password) {
        Optional<User> user = userDao.getAll().stream().filter(u -> u.getPassword().equals(password) && u.getUsername().equals(username)).findFirst();

        if (user.isPresent()) {
            String accessToken = UUID.randomUUID().toString();
            user.get().setAccessToken(accessToken);
            return accessToken;
        }

        throw new UnauthorizedException("Provided credentials are not in base");
    }

    public void logout(String accessToken) {
        if (accessToken == null || !this.authorizationManager.isAuthorized(accessToken)) {
            throw new UnauthorizedException("Unauthorized user");
        }

        User user = userDao.getAll().stream().filter(u -> u.getAccessToken().equals(accessToken)).findFirst().get();
        user.setAccessToken(null);
    }

}

package com.krypczyk.restservice.service;

import com.krypczyk.restservice.storage.UserStorage;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationManager {

    public boolean isAuthorized(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            return false;
        }
        return UserStorage.getInstance().getStorage().values().stream().anyMatch(u -> u.getAccessToken() != null && u.getAccessToken().equals(accessToken));
    }
}

package com.krypczyk.restservice.service;

import com.krypczyk.restservice.storage.UserStorage;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthorizationManager {

    public boolean isAuthorized(String accessToken) {
        if (Objects.isNull(accessToken) || accessToken.isEmpty()) {
            return false;
        }
        return UserStorage.getInstance().getStorage().values().stream().anyMatch(u -> !Objects.isNull(u.getAccessToken()) && u.getAccessToken().equals(accessToken));
    }
}

package com.krypczyk.restservice.service;

import com.krypczyk.restservice.storage.UserStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthorizationManagerTest {

    @Autowired
    private AuthorizationManager authorizationManager;

    @ParameterizedTest
    @ValueSource(strings = {"a", "", "invalidToken"})
    void testIsAuthorizedWithInvalidAccessToken(String param) {
        boolean expectedResult = false;

        boolean result = this.authorizationManager.isAuthorized(param);

        assertEquals(expectedResult, result);
    }

    @Test
    void testIsAuthorizedWithValidAccessToken() {
        boolean expectedResult = true;
        UserStorage.getInstance().getStorage().get(1L).setAccessToken("token");

        boolean result = this.authorizationManager.isAuthorized("token");

        assertEquals(expectedResult, result);
    }
}
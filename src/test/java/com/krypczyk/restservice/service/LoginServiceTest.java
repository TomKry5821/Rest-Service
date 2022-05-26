package com.krypczyk.restservice.service;

import com.krypczyk.restservice.Exception.UnauthorizedException;
import com.krypczyk.restservice.storage.UserStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    void testLoginWithValidInput() {
        String username = "user";
        String password = "password";

        this.loginService.login(username, password);

        assertDoesNotThrow(() -> UnauthorizedException.class);
    }

    @Test
    void testLoginWithInvalidInput() {
        String username = null;
        String password = null;

        assertThrows(UnauthorizedException.class, () -> this.loginService.login(username, password));
    }

    @Test
    void testLogoutWithValidToken() {
        String accessToken = "validToken";
        UserStorage.getInstance().getStorage().get(1L).setAccessToken(accessToken);

        this.loginService.logout(accessToken);

        assertDoesNotThrow(() -> UnauthorizedException.class);
    }

    @Test
    void testLogoutWithInvalidToken() {
        String accessToken = null;
        UserStorage.getInstance().getStorage().get(1L).setAccessToken(accessToken);

        assertThrows(UnauthorizedException.class, () -> this.loginService.logout(accessToken));
    }
}
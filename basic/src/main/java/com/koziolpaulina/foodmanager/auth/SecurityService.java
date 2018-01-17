package com.koziolpaulina.foodmanager.auth;

import org.springframework.security.core.Authentication;

public interface SecurityService {
    public String findLoggedInUsername();
    public Authentication autoLogin(String username, String password);
}

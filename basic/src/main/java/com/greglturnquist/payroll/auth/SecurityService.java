package com.greglturnquist.payroll.auth;

import org.springframework.security.core.Authentication;

/**
 * Created by Paulina on 06.01.2018.
 */
public interface SecurityService {
    public String findLoggedInUsername();
    public Authentication autoLogin(String username, String password);
}

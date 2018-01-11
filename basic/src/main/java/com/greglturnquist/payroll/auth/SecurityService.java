package com.greglturnquist.payroll.auth;

/**
 * Created by Paulina on 06.01.2018.
 */
public interface SecurityService {
    public String findLoggedInUsername();
    public void autoLogin(String username, String password);
}

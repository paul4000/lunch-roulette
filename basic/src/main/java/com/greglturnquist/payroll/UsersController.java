package com.greglturnquist.payroll;


import com.greglturnquist.payroll.auth.SecurityService;
import com.greglturnquist.payroll.auth.login.Credentials;
import com.greglturnquist.payroll.auth.login.User;
import com.greglturnquist.payroll.services.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paulina on 06.01.2018.
 */
@RestController
@RequestMapping(path = "/users")
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    final private Logger logger = Logger.getLogger(UsersController.class);

    @PostMapping(path = "/register")
    public void register(@RequestBody User user) {
        logger.log(Level.INFO, "Adding new user");

        User responseBody = userService.addNewUser(user);

        securityService.autoLogin(user.getUsername(), user.getPassword());
    }

    @PostMapping(path = "/login")
    public void login(@RequestBody Credentials credentials) {
        logger.log(Level.INFO, "Logging");

        securityService.autoLogin(credentials.getUsername(), credentials.getPassword());

    }
}

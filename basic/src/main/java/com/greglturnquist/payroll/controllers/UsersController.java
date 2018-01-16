package com.greglturnquist.payroll.controllers;


import com.greglturnquist.payroll.auth.SecurityService;
import com.greglturnquist.payroll.auth.login.Credentials;
import com.greglturnquist.payroll.auth.login.User;
import com.greglturnquist.payroll.services.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        String password = user.getPassword();

        User responseBody = userService.addNewUser(user);

        securityService.autoLogin(user.getUsername(), password);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Void> login(@RequestBody Credentials credentials) {
        logger.log(Level.INFO, "Logging");

        Authentication authentication = securityService.autoLogin(credentials.getUsername(), credentials.getPassword());

        if (authentication == null) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<Void>(HttpStatus.OK);


    }
    @GetMapping(path="/exists")
    public ResponseEntity<Void> userExists(@RequestParam(value="username") String username){

        logger.log(Level.INFO, "Checking if user exists");

        if(userService.userExists(username)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}

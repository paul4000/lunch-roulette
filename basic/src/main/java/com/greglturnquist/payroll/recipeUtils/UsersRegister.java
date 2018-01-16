package com.greglturnquist.payroll.recipeUtils;

import com.greglturnquist.payroll.auth.login.User;
import com.greglturnquist.payroll.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paulina on 16.01.2018.
 */
@Component
public class UsersRegister {

    @Autowired
    private UsersRepository usersRepository;

    private Map<String, User> userMap;

    @PostConstruct
    public void init() {
        userMap = new HashMap<>();
        usersRepository.findAll().forEach(user -> userMap.put(user.getUsername(), user));
    }

    public boolean isRegistered(String username){
        return userMap.containsKey(username);
    }

    public User getUser(String username){

        if(isRegistered(username)) {
            return userMap.get(username);
        } else {
            return null;
        }
    }

    public void addUser(User user){
        userMap.put(user.getUsername(), user);
    }


}

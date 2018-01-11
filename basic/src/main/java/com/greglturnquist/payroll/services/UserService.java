package com.greglturnquist.payroll.services;


import com.greglturnquist.payroll.auth.login.User;
import com.greglturnquist.payroll.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Paulina on 06.01.2018.
 */
@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User addNewUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return usersRepository.save(user);
    }

    public Iterable<User> getAllUsers() {

        return usersRepository.findAll();
    }
}

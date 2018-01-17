package com.greglturnquist.payroll.services;


import com.greglturnquist.payroll.auth.login.User;
import com.greglturnquist.payroll.recipeUtils.UsersRegister;
import com.greglturnquist.payroll.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsersRegister usersRegister;

    public User addNewUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        usersRegister.addUser(user);

        return usersRepository.save(user);
    }

    public Iterable<User> getAllUsers() {

        return usersRepository.findAll();
    }

    public boolean userExists(String username){
        return usersRegister.isRegistered(username);
    }
}

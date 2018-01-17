package com.koziolpaulina.foodmanager.repositories;

import com.koziolpaulina.foodmanager.auth.login.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

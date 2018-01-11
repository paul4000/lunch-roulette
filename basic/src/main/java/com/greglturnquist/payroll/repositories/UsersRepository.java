package com.greglturnquist.payroll.repositories;

import com.greglturnquist.payroll.auth.login.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paulina on 05.01.2018.
 */
public interface UsersRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

package com.mycard.users.service;

import com.mycard.users.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);

    List<User> getUserList();

    User saveUser(User user);

    Optional<User> updateUser(User user);
}

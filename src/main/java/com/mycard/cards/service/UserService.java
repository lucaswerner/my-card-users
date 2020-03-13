package com.mycard.cards.service;

import com.mycard.cards.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);

    List<User> getUserList();

    User saveUser(User user);

    Optional<User> updateUser(User user);
}

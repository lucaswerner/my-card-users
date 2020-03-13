package com.mycard.cards.service.impl;

import com.mycard.cards.entity.User;
import com.mycard.cards.repository.UserRepository;
import com.mycard.cards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateUser(User user) {
        final Optional<User> optionalUserFromDB = getUser(user.getId());

        if (optionalUserFromDB.isEmpty())
            return optionalUserFromDB;

        final User userFromDB = optionalUserFromDB.get();

        userFromDB.setEmail(user.getEmail());
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setStActive(user.getStActive());

        return Optional.of(userRepository.save(userFromDB));
    }
}

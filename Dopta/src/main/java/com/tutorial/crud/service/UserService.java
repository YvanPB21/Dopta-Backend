package com.tutorial.crud.service;

import com.tutorial.crud.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);
    Optional<User> findById(Integer id);
    List<User> listAllUser();
    User edit(User user, Integer id);
    void deleteById(Integer id);
}
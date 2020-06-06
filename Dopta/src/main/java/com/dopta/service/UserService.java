package com.dopta.service;


import com.dopta.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User getUser(Integer id);
    User save(User user);
    Optional<User> findById(Integer id);
    List<User> listAllUser();
    User edit(User user, Integer id);
    void deleteById(Integer id);

}
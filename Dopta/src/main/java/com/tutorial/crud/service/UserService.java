package com.tutorial.crud.service;

import com.tutorial.crud.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUserById(Integer userId);
    Page<User> getAllUsers(Pageable pageable);
    User createUser(User user);
    User updateUser(Integer id, User userDetails);
    ResponseEntity<?> deleteUser(Integer userId);
}
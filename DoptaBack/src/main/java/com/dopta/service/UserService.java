package com.dopta.service;

import com.dopta.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User getUserById(Integer userId);

    Page<User> getAllUsers(Pageable pageable);

    User createUser(User user, Integer districtId, Integer locatableId, Integer genderId);

    User updateUser(Integer id, Integer districtId, Integer locatableId, Integer genderId, User userDetails);

    ResponseEntity<?> deleteUser(Integer userId);
}
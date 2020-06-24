package com.dopta.service;

import com.dopta.model.UserSignIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserSignIn> getAllUsers(Pageable pageable);
    public UserSignIn registerUser(UserSignIn user);
}

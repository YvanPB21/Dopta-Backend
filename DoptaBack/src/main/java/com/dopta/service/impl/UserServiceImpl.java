package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.User;
import com.dopta.repository.UserRepository;
import com.dopta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Integer id, User userDetails) {
        return userRepository.findById(id).map(us -> {
            us.setEmail_address(userDetails.getEmail_address());
            us.setUsername(userDetails.getUsername());
            us.setPassword(userDetails.getPassword());
            return userRepository.save(us);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteUser(Integer userId) {
        return userRepository.findById(userId).map(us -> {
            userRepository.delete(us);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }
}


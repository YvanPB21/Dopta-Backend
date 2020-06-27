package com.tutorial.crud.service.impl;

import com.tutorial.crud.model.User;
import com.tutorial.crud.repository.UserRepository;
import com.tutorial.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.DiscriminatorValue;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User edit(User user, Integer id) {
        return userRepository.findById(id).map(u->{
            u.setUsername(user.getUsername());
            u.setEmail_address(user.getEmail_address());
            u.setPassword(user.getPassword());
            return userRepository.save(u);
        }).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Integer id)
    {
        userRepository.deleteById(id);
    }

}


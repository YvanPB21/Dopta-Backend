package com.dopta.service.impl;


import com.dopta.model.UserSignIn;
import com.dopta.repository.UserRepository;
import com.dopta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${hampserver.default-rol}")
    private Long DEFAULT_ROL;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSignIn user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Usuario no existe", username));
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }

    @Override
    public Page<UserSignIn> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public UserSignIn registerUser(UserSignIn user) {
        UserSignIn userNew = userRepository.save(user); //save tabla user y person
        userRepository.register(userNew.getIdUser(), DEFAULT_ROL);//save tabla user_roles
        return userNew;
    }
}

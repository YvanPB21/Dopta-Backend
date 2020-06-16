package com.dopta.service.impl;

import com.dopta.dto.converter.UserDTOConverter;
import com.dopta.dto.user.CreateUserDTO;
import com.dopta.dto.user.EditUserDTO;
import com.dopta.dto.user.UserDTO;
import com.dopta.model.User;
import com.dopta.repository.UserRepository;
import com.dopta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.DiscriminatorValue;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDTOConverter userDTOConverter;

    @Override
    @Transactional
    public User save(CreateUserDTO userDTO) {
        User user = userDTOConverter.convertToEntity(userDTO);
        return userRepository.save(user);
    }

    @Override
    public Optional<UserDTO> findById(Integer id) {
        return userRepository.findById(id).map(userDTOConverter::convertToDto);
    }

    @Override
    public List<UserDTO> listAllUser() {
        List<UserDTO> usersDTO = userRepository.findAll().stream().map(userDTOConverter::convertToDto)
                .collect(Collectors.toList());
        return usersDTO;
    }

    @Override
    @Transactional
    public User edit(EditUserDTO userDTO, Integer id) {
        return userRepository.findById(id).map(u->{
            u.setUsername(userDTO.getUsername());
            u.setEmail_address(userDTO.getEmail_address());
            u.setPassword(userDTO.getPassword());
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

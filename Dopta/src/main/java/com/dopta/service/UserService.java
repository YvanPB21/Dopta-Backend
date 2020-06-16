package com.dopta.service;


import com.dopta.dto.pet.CreatePetDTO;
import com.dopta.dto.pet.EditPetDTO;
import com.dopta.dto.pet.PetDTO;
import com.dopta.dto.user.CreateUserDTO;
import com.dopta.dto.user.EditUserDTO;
import com.dopta.dto.user.UserDTO;
import com.dopta.model.Pet;
import com.dopta.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(CreateUserDTO userDTO);
    Optional<UserDTO> findById(Integer id);
    List<UserDTO> listAllUser();
    User edit(EditUserDTO userDTO, Integer id);
    void deleteById(Integer id);
}
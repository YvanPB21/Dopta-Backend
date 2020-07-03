package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.Pet;
import com.dopta.model.User;
import com.dopta.repository.DistrictRepository;
import com.dopta.repository.GenderRepository;
import com.dopta.repository.LocatableRepository;
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

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private LocatableRepository locatableRepository;

    @Autowired
    private GenderRepository genderRepository;

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
    public User createUser(User user, Integer districtId, Integer locatableId, Integer genderId) {
        User newUser = new User();
        newUser.setDistrict(districtRepository.findById(districtId).orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId)));
        newUser.setLocatable(locatableRepository.findById(locatableId).orElseThrow(() -> new ResourceNotFoundException("Locatable", "Id", locatableId)));
        newUser.setGender(genderRepository.findById(genderId).orElseThrow(() -> new ResourceNotFoundException("Gender", "Id", genderId)));
        newUser.setEmail_address(user.getEmail_address());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setProfile_pic_url(user.getProfile_pic_url());
        newUser.setDate_of_registration(user.getDate_of_registration());
        newUser.setNames(user.getNames());
        newUser.setLast_names(user.getLast_names());
        newUser.setDni(user.getDni());
        newUser.setDate_of_birth(user.getDate_of_birth());
        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public User updateUser(Integer id, Integer districtId, Integer locatableId, Integer genderId, User userDetails) {
        if (!districtRepository.existsById(districtId))
            throw new ResourceNotFoundException("District", "Id", districtId);
        if (!locatableRepository.existsById(locatableId))
            throw new ResourceNotFoundException("Locatable", "Id", locatableId);
        if (!genderRepository.existsById(genderId))
            throw new ResourceNotFoundException("Gender", "Id", genderId);
        return userRepository.findById(id).map(us -> {
            us.setDistrict(districtRepository.findById(districtId).orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId)));
            us.setLocatable(locatableRepository.findById(locatableId).orElseThrow(() -> new ResourceNotFoundException("Locatable", "Id", locatableId)));
            us.setGender(genderRepository.findById(genderId).orElseThrow(() -> new ResourceNotFoundException("Gender", "Id", genderId)));
            us.setEmail_address(userDetails.getEmail_address());
            us.setUsername(userDetails.getUsername());
            us.setPassword(userDetails.getPassword());
            us.setProfile_pic_url(userDetails.getProfile_pic_url());
            us.setDate_of_registration(userDetails.getDate_of_registration());
            us.setNames(userDetails.getNames());
            us.setLast_names(userDetails.getLast_names());
            us.setDni(userDetails.getDni());
            us.setDate_of_birth(userDetails.getDate_of_birth());
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


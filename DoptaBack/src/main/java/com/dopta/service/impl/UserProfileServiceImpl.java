package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.UserProfile;
import com.dopta.repository.*;
import com.dopta.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private LocatableRepository locatableRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserProfile getUserProfileByUserId(Integer userId) {
        return userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "UserProfile not found with userId " + userId
                ));
    }

    @Override
    public Page<UserProfile> getAllUserProfiles(Pageable pageable) {
        return userProfileRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public UserProfile createUserProfile(Integer districtId, Integer locatableId, Integer userId, UserProfile userProfileDetails) {
        UserProfile newUserProfile = new UserProfile();
        newUserProfile.setProfile_pic_url(userProfileDetails.getProfile_pic_url());
        newUserProfile.setDate_of_registration(userProfileDetails.getDate_of_registration());
        newUserProfile.setDistrict(districtRepository.findById(districtId).orElse(null));
        newUserProfile.setLocatable(locatableRepository.findById(locatableId).orElse(null));
        newUserProfile.setUser(userRepository.findById(userId).orElse(null));
        return userProfileRepository.save(newUserProfile);
    }

    @Override
    @Transactional
    public UserProfile updateUserProfile(Integer userProfileId, Integer districtId, Integer locatableId, UserProfile userProfileDetails) {
        if (!districtRepository.existsById(districtId))
            throw new ResourceNotFoundException("District", "Id", districtId);
        if (!locatableRepository.existsById(locatableId))
            throw new ResourceNotFoundException("Locatable", "Id", locatableId);
        return userProfileRepository.findByUserId(userProfileId).map(us -> {
            us.setDistrict(districtRepository.findById(districtId).orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId)));
            us.setLocatable(locatableRepository.findById(locatableId).orElseThrow(() -> new ResourceNotFoundException("Locatable", "Id", locatableId)));
            us.setProfile_pic_url(userProfileDetails.getProfile_pic_url());
            return userProfileRepository.save(us);
        }).orElseThrow(() -> new ResourceNotFoundException("UserProfile", "Id", userProfileId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteUserProfile(Integer userProfileId) {
        return userProfileRepository.findByUserId(userProfileId).map(usp -> {
            userProfileRepository.delete(usp);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserProfile", "Id", userProfileId));
    }
}

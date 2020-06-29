package com.dopta.service;

import com.dopta.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserProfileService {
    UserProfile getUserProfileByUserId(Integer userId);

    Page<UserProfile> getAllUserProfiles(Pageable pageable);

    UserProfile createUserProfile(Integer districtId, Integer locatableId, Integer userId, UserProfile userProfileDetails);

    UserProfile updateUserProfile(Integer userProfileId, Integer districtId, Integer locatableId, UserProfile userProfileDetails);

    ResponseEntity<?> deleteUserProfile(Integer userProfileId);
}

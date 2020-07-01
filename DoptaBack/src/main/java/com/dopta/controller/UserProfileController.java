package com.dopta.controller;

import com.dopta.model.UserProfile;
import com.dopta.resource.SaveUserProfileResource;
import com.dopta.resource.UserProfileResource;
import com.dopta.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserProfileController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/userProfiles")
    public List<UserProfileResource> getAllUserProfiles(Pageable pageable) {
        List<UserProfileResource> resources = userProfileService.getAllUserProfiles(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @GetMapping("/userProfiles/{userId}")
    public UserProfileResource getUserProfileByUserId(@PathVariable(name = "userId") Integer userId) {
        return convertToResource(userProfileService.getUserProfileByUserId(userId));
    }

    @PostMapping("/userProfiles/{userId}/{districtId}/{locatableId}")
    public UserProfileResource createUserProfile(@PathVariable(name = "userId") Integer userId,
                                                 @PathVariable(name = "districtId") Integer districtId,
                                                 @PathVariable(name = "locatableId") Integer locatableId,
                                                 @Valid @RequestBody SaveUserProfileResource resource) {
        return convertToResource(userProfileService.createUserProfile(districtId, locatableId, userId, convertToEntity(resource)));
    }

    @PutMapping("/userProfiles/{userId}/{districtId}/{locatableId}")
    public UserProfileResource updateUserProfile(@PathVariable(name = "userId") Integer userId,
                                                 @PathVariable(name = "districtId") Integer districtId,
                                                 @PathVariable(name = "locatableId") Integer locatableId,
                                                 @Valid @RequestBody SaveUserProfileResource resource) {
        return convertToResource(userProfileService.updateUserProfile(userId, districtId, locatableId, convertToEntity(resource)));
    }

    @DeleteMapping("/userProfiles/{userId}")
    public ResponseEntity<?> deleteUserProfile(@PathVariable(name = "userId") Integer userId) {
        return userProfileService.deleteUserProfile(userId);
    }

    private UserProfile convertToEntity(SaveUserProfileResource resource) {
        return mapper.map(resource, UserProfile.class);
    }

    private UserProfileResource convertToResource(UserProfile entity) {
        return mapper.map(entity, UserProfileResource.class);
    }

}

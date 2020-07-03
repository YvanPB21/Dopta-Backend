package com.dopta.controller;

import com.dopta.model.User;
import com.dopta.resource.SaveUserResource;
import com.dopta.resource.UserResource;
import com.dopta.service.UserService;
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
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserResource> getAllUsers(Pageable pageable) {
        List<UserResource> users = userService.getAllUsers(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return users;
    }

    @GetMapping("/users/{id}")
    public UserResource getUserById(@PathVariable(name = "id") Integer userId) {
        return convertToResource(userService.getUserById(userId));
    }

    @PostMapping("/users/{districtId}/{locatableId}/{genderId}")
    public UserResource createUser(@PathVariable(name = "districtId") Integer districtId,
                                   @PathVariable(name = "locatableId") Integer locatableId,
                                   @PathVariable(name = "genderId") Integer genderId,
                                   @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.createUser(convertToEntity(resource),districtId,locatableId,genderId));
    }

    @PutMapping("/users/{id}/{districtId}/{locatableId}/{genderId}")
    public UserResource updateUser(@PathVariable(name = "id") Integer userId,
                                   @PathVariable(name = "districtId") Integer districtId,
                                   @PathVariable(name = "locatableId") Integer locatableId,
                                   @PathVariable(name = "genderId") Integer genderId, @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.updateUser(userId,districtId,locatableId,genderId,convertToEntity(resource)));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Integer userId) {
        return userService.deleteUser(userId);
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }


}
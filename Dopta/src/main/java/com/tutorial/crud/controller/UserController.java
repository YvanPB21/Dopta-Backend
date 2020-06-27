package com.tutorial.crud.controller;

import com.tutorial.crud.model.User;
import com.tutorial.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UserDTO> users=userService.listAllUser();

        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Integer id) {
        Optional<UserDTO> result = userService.findById(id);
        if (!result.isPresent())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<User> newUser(@RequestBody CreateUserDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody EditUserDTO userDTO, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.edit(userDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
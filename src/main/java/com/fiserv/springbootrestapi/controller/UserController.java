package com.fiserv.springbootrestapi.controller;

import com.fiserv.springbootrestapi.entity.User;
import com.fiserv.springbootrestapi.exception.ResourceNotFoundException;
import com.fiserv.springbootrestapi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private IUserService userService;

    //build create user REST API
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //build get user by id REST API
    @GetMapping("/{id}")
    public  ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException(String.format("User not found for the user id %d", id));
        }

        return ResponseEntity.ok(user);
    }

    //build get all users REST API
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userService.updateUser(user, id);

        if (updatedUser ==  null) {
            throw new ResourceNotFoundException(String.format("User not found for the user id %d", id));
        }

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.ok("User Deleted");
    }

}
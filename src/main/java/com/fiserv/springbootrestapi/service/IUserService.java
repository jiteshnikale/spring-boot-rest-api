package com.fiserv.springbootrestapi.service;

import com.fiserv.springbootrestapi.entity.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(User user, Long id);

    void deleteUser(Long id);
}

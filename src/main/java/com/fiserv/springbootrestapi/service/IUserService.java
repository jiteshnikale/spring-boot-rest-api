package com.fiserv.springbootrestapi.service;

import com.fiserv.springbootrestapi.dto.UserDto;
import com.fiserv.springbootrestapi.entity.User;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(User user, Long id);

    void deleteUser(Long id);
}

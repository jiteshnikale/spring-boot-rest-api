package com.fiserv.springbootrestapi.service.impl;

import com.fiserv.springbootrestapi.dto.UserDto;
import com.fiserv.springbootrestapi.entity.User;
import com.fiserv.springbootrestapi.mapper.UserMapper;
import com.fiserv.springbootrestapi.repository.IUserRepository;
import com.fiserv.springbootrestapi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    IUserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert UserDto into JPA User entity
        /*User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getFirstName(),
                userDto.getEmail()
        );*/
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        //Convert JPA User entity into UserDto
        /*UserDto savedUserDto = new UserDto(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );*/
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            return null;
        }

        return UserMapper.mapToUserDto(user.get());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(User user, Long id) {
        Optional<User> foundUser = userRepository.findById(id);

        if (!foundUser.isPresent()) {
            return null;
        }

        User existingUser = foundUser.get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(existingUser);


        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

package com.fiserv.springbootrestapi.service.impl;

import com.fiserv.springbootrestapi.dto.UserDto;
import com.fiserv.springbootrestapi.entity.User;
import com.fiserv.springbootrestapi.exception.EmailAlreadyExistsException;
import com.fiserv.springbootrestapi.exception.ResourceNotFoundException;
import com.fiserv.springbootrestapi.mapper.IAutoUserMapper;
import com.fiserv.springbootrestapi.mapper.UserMapper;
import com.fiserv.springbootrestapi.repository.IUserRepository;
import com.fiserv.springbootrestapi.service.IUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    IUserRepository userRepository;

    ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert UserDto into JPA User entity
        /*User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getFirstName(),
                userDto.getEmail()
        );*/
        //User user = UserMapper.mapToUser(userDto);
        //User user = modelMapper.map(userDto, User.class);

        Optional<User> existingEmailUser = userRepository.findByEmail(userDto.getEmail());

        if (existingEmailUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already exists");
        }

        User user = IAutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        //Convert JPA User entity into UserDto
        /*UserDto savedUserDto = new UserDto(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );*/
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User not found for the user id %d", id))
        );

        //return UserMapper.mapToUserDto(user);
        //return modelMapper.map(user, UserDto.class);
        return IAutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        /*return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());*/
        /*return users.stream().map(x -> modelMapper.map(x, UserDto.class))
                .collect(Collectors.toList());*/
        return users.stream().map(x -> IAutoUserMapper.MAPPER.mapToUserDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user, Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User not found for the user id %d", id))
        );

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User updatedUser = userRepository.save(existingUser);


        //return UserMapper.mapToUserDto(updatedUser);
        //return modelMapper.map(updatedUser, UserDto.class);
        return IAutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User not found for the user id %d", id))
        );
        userRepository.deleteById(id);
    }
}

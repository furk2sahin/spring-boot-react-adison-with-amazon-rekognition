package com.furkannsahin.adison.service;

import com.furkannsahin.adison.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserByUserName(String userName);
    UserDto getUserById(Long id);
    UserDto addUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}

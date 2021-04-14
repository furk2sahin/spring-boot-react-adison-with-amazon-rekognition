package com.furkannsahin.adison.service.impl;

import com.furkannsahin.adison.dto.UserDto;
import com.furkannsahin.adison.mapper.UserMapper;
import com.furkannsahin.adison.model.Users;
import com.furkannsahin.adison.repository.UserRepository;
import com.furkannsahin.adison.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toUserDtos(userRepository.findAll());
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        return userMapper.toUserDto(userRepository.findByUserName(userName).orElse(null));
    }

    @Override
    public UserDto getUserById(Long id) {
        return userMapper.toUserDto(userRepository.getOne(id));
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        Users user = userMapper.toUser(userDto);
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Users user = userMapper.toUser(userDto);
        Users userToUpdate = userRepository.getOne(id);
        userToUpdate.setActive(user.isActive());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFullName(user.getFullName());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setUserType(user.getUserType());
        userToUpdate.setDescription(user.getDescription());
        userToUpdate.setImage(user.getImage());
        return userMapper.toUserDto(userRepository.save(userToUpdate));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

package com.furkannsahin.adison.service.impl;

import com.furkannsahin.adison.dto.UserRoleDto;
import com.furkannsahin.adison.mapper.UserRoleMapper;
import com.furkannsahin.adison.model.User;
import com.furkannsahin.adison.model.UserRole;
import com.furkannsahin.adison.repository.UserRepository;
import com.furkannsahin.adison.repository.UserRoleRepository;
import com.furkannsahin.adison.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final UserRoleMapper userRoleMapper;

    @Override
    public List<UserRoleDto> getAllUserRoles() {
        return userRoleMapper.toUserRoleDtos(userRoleRepository.findAll());
    }

    @Override
    public List<UserRoleDto> getUserRoleByUserId(Long userId) {
        return userRoleMapper.toUserRoleDtos(userRoleRepository.findAllByUserId(userId).get());
    }

    @Override
    public UserRoleDto addUserRole(UserRoleDto userRoleDto) {
        User user = userRepository.findById(userRoleDto.getUserDto().getId()).orElse(null);
        if(user != null){
            UserRole userRole = userRoleMapper.toUserRole(userRoleDto);
            userRole.setUser(user);
            return userRoleMapper.toUserRoleDto(userRoleRepository.save(userRole));
        }
        return null;
    }

    @Override
    public void deleteUserRole(Long id) {
        userRoleRepository.deleteById(id);
    }
}

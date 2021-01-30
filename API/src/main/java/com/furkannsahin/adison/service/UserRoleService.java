package com.furkannsahin.adison.service;

import com.furkannsahin.adison.dto.UserRoleDto;
import java.util.List;

public interface UserRoleService {
    List<UserRoleDto> getAllUserRoles();
    List<UserRoleDto> getUserRoleByUserId(Long userId);
    UserRoleDto addUserRole(UserRoleDto userRoleDto);
    void deleteUserRole(Long id);
}

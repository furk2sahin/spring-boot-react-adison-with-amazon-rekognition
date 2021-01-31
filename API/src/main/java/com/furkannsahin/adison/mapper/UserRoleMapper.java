package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.UserRoleDto;
import com.furkannsahin.adison.model.UserRole;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    @Named("toUserRoleDto")
    @Mapping(target = "userDto", source = "user")
    UserRoleDto toUserRoleDto(UserRole userRole);

    @Named("toUserRole")
    @Mapping(target = "user", source = "userDto")
    UserRole toUserRole(UserRoleDto userRoleDto);

    @IterableMapping(qualifiedByName = "toUserRoleDtos")
    List<UserRoleDto> toUserRoleDtos(List<UserRole> userRoles);
}

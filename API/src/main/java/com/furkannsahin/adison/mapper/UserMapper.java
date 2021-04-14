package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.UserDto;
import com.furkannsahin.adison.model.Users;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Named("toUserDto")
    @Mapping(target = "companyDto", source = "company", ignore = true)
    @Mapping(target = "postsDto", source = "posts", ignore = true)
    UserDto toUserDto(Users user);

    @Named("toUser")
    @Mapping(target = "company", source = "companyDto", ignore = true)
    @Mapping(target = "posts", source = "postsDto", ignore = true)
    Users toUser(UserDto userDto);

    @IterableMapping(qualifiedByName = "toUserDtos")
    List<UserDto> toUserDtos(List<Users> users);
}

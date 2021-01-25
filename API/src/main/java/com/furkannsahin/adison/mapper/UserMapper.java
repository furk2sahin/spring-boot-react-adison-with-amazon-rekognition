package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.UserDto;
import com.furkannsahin.adison.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Named("toUserDto")
    UserDto toUserDto(User user);

    @Named("toUser")
    User toUser(UserDto userDto);

    @IterableMapping(qualifiedByName = "toUserDtos")
    List<UserDto> toUserDtos(List<User> users);

    @IterableMapping(qualifiedByName = "toUsers")
    List<User> toUsers(List<UserDto> userDtos);

}

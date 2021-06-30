package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.UserAdPostDto;
import com.furkannsahin.adison.dto.UserDto;
import com.furkannsahin.adison.model.UserAdPost;
import com.furkannsahin.adison.model.Users;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Named("toUserAdPostDto")
    @Mapping(target = "adDto", source = "ad")
    @Mapping(target = "userDto", source = "user")
    UserAdPostDto toUserAdPostDto(UserAdPost userAdPost);

    @Named("toUserAdPost")
    @Mapping(target = "ad", source = "adDto")
    @Mapping(target = "user", source = "userDto")
    UserAdPost toUserAdPost(UserAdPostDto userAdPostDto);

    @Named("toUserDto")
    @Mapping(target = "companyDto", source = "company")
    @Mapping(target = "postsDto", source = "posts", qualifiedByName = "toUserAdPostDto")
    UserDto toUserDto(Users user);

    @Named("toUser")
    @Mapping(target = "company", source = "companyDto")
    @Mapping(target = "posts", source = "postsDto", qualifiedByName = "toUserAdPost")
    Users toUser(UserDto userDto);

    @IterableMapping(qualifiedByName = "toUserDto")
    List<UserDto> toUserDtos(List<Users> users);
}

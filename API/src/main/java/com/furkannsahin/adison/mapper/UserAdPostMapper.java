package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.UserAdPostDto;
import com.furkannsahin.adison.model.UserAdPost;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAdPostMapper {
    @Named("toUserAdPostDto")
    @Mapping(target = "adDto", source = "ad")
    @Mapping(target = "userDto", source = "user")
    UserAdPostDto toUserAdPostDto(UserAdPost userAdPost);

    @Named("toUserAdPost")
    @Mapping(target = "ad", source = "adDto")
    @Mapping(target = "user", source = "userDto")
    UserAdPost toUserAdPost(UserAdPostDto userAdPostDto);

    @IterableMapping(qualifiedByName = "toUserAdPostDtos")
    List<UserAdPostDto> toUserAdPostDtos(List<UserAdPost> userAdPosts);
}

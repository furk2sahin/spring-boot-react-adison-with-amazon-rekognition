package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.UserAdPostDto;
import com.furkannsahin.adison.model.UserAdPost;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAdPostMapper {
    @Named("toUserAdPostDto")
    UserAdPostDto toUserAdPostDto(UserAdPost userAdPost);

    @Named("toUserAdPost")
    UserAdPost toUserAdPost(UserAdPostDto userAdPostDto);

    @IterableMapping(qualifiedByName = "toUserAdPostDtos")
    List<UserAdPostDto> toUserAdPostDtos(List<UserAdPost> userAdPosts);

    @IterableMapping(qualifiedByName = "toUserAdPosts")
    List<UserAdPost> toUserAdPosts(List<UserAdPostDto> userAdPostDtos);
}

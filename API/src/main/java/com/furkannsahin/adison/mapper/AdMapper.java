package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.AdDto;
import com.furkannsahin.adison.dto.UserAdPostDto;
import com.furkannsahin.adison.model.Ad;
import com.furkannsahin.adison.model.UserAdPost;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Named("toUserAdPostDto")
    @Mapping(target = "adDto", source = "ad")
    @Mapping(target = "userDto", source = "user")
    UserAdPostDto toUserAdPostDto(UserAdPost userAdPost);

    @Named("toUserAdPost")
    @Mapping(target = "ad", source = "adDto")
    @Mapping(target = "user", source = "userDto")
    UserAdPost toUserAdPost(UserAdPostDto userAdPostDto);

    @Named("toAdDto")
    @Mapping(target = "companyDto", source = "company")
    @Mapping(target = "postsDto", source = "posts", qualifiedByName = "toUserAdPostDto")
    AdDto toAdDto(Ad ad);

    @Named("toAd")
    @Mapping(target = "company", source = "companyDto")
    @Mapping(target = "posts", source = "postsDto", qualifiedByName = "toUserAdPost")
    Ad toAd(AdDto adDto);

    @IterableMapping(qualifiedByName = "toAdDto")
    List<AdDto> toAdDtos(List<Ad> ads);
}

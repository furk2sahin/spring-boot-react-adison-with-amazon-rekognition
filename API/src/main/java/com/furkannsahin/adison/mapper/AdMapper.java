package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.AdDto;
import com.furkannsahin.adison.model.Ad;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {
    @Named("toAdDto")
    @Mapping(target = "companyDto", source = "company")
    @Mapping(target = "postsDto", source = "posts")
    AdDto toAdDto(Ad ad);

    @Named("toAd")
    @Mapping(target = "company", source = "companyDto")
    @Mapping(target = "posts", source = "postsDto")
    Ad toAd(AdDto adDto);

    @IterableMapping(qualifiedByName = "toAdDtos")
    List<AdDto> toAdDtos(List<Ad> ads);
}

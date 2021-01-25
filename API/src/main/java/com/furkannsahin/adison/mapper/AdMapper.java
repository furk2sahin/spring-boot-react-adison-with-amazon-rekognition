package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.AdDto;
import com.furkannsahin.adison.model.Ad;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdMapper {
    @Named("toAdDto")
    AdDto toAdDto(Ad ad);

    @Named("toAd")
    Ad toAd(AdDto adDto);

    @IterableMapping(qualifiedByName = "toAdDtos")
    List<AdDto> toAdDtos(List<Ad> ads);

    @IterableMapping(qualifiedByName = "toAds")
    List<Ad> toAds(List<AdDto> adsDto);


}

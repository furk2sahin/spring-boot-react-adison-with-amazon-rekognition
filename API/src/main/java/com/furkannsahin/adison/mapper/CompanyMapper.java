package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.AdDto;
import com.furkannsahin.adison.dto.CompanyDto;
import com.furkannsahin.adison.dto.UserAdPostDto;
import com.furkannsahin.adison.model.Ad;
import com.furkannsahin.adison.model.Company;
import com.furkannsahin.adison.model.UserAdPost;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

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

    @Named("toCompanyDto")
    @Mapping(target = "userDto", source = "user")
    @Mapping(target = "adsDto", source = "ads", qualifiedByName = "toAdDto")
    CompanyDto toCompanyDto(Company company);

    @Named("toCompany")
    @Mapping(target = "user", source = "userDto")
    @Mapping(target = "ads", source = "adsDto", qualifiedByName = "toAd")
    Company toCompany(CompanyDto companyDto);

    @IterableMapping(qualifiedByName = "toCompanyDto")
    List<CompanyDto> toCompanyDtos(List<Company> companies);
}

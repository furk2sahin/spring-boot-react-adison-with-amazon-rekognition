package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.CompanyDto;
import com.furkannsahin.adison.model.Company;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @Named("toCompanyDto")
    @Mapping(target = "userDto", source = "user")
    @Mapping(target = "adsDto", source = "ads")
    CompanyDto toCompanyDto(Company company);

    @Named("toCompany")
    @Mapping(target = "user", source = "userDto")
    @Mapping(target = "ads", source = "adsDto")
    Company toCompany(CompanyDto companyDto);

    @IterableMapping(qualifiedByName = "toCompanyDtos")
    List<CompanyDto> toCompanyDtos(List<Company> companies);
}

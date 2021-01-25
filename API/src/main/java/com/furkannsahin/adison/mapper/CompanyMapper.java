package com.furkannsahin.adison.mapper;

import com.furkannsahin.adison.dto.CompanyDto;
import com.furkannsahin.adison.model.Company;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @Named("toCompanyDto")
    CompanyDto toCompanyDto(Company company);

    @Named("toCompany")
    Company toCompany(CompanyDto companyDto);

    @IterableMapping(qualifiedByName = "toCompanyDtos")
    List<CompanyDto> toCompanyDtos(List<Company> companies);

    @IterableMapping(qualifiedByName = "toCompanies")
    List<Company> toCompanies(List<CompanyDto> companyDtos);
}

package com.furkannsahin.adison.service;

import com.furkannsahin.adison.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAllCompanies();
    CompanyDto getCompanyById(Long id);
    CompanyDto addCompany(CompanyDto companyDto);
    CompanyDto updateCompany(Long id, CompanyDto companyDto);
    void deleteCompany(Long id);
}

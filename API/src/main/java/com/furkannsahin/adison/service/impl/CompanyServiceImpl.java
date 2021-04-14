package com.furkannsahin.adison.service.impl;

import com.furkannsahin.adison.dto.CompanyDto;
import com.furkannsahin.adison.mapper.CompanyMapper;
import com.furkannsahin.adison.model.Company;
import com.furkannsahin.adison.model.Users;
import com.furkannsahin.adison.repository.CompanyRepository;
import com.furkannsahin.adison.repository.UserRepository;
import com.furkannsahin.adison.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyMapper.toCompanyDtos(companyRepository.findAll());
    }

    @Override
    public CompanyDto getCompanyById(Long id) {
        return companyMapper.toCompanyDto(companyRepository.getOne(id));
    }

    @Override
    public CompanyDto addCompany(CompanyDto companyDto) {
        Company companyControl = companyRepository.findByUserId(companyDto.getUserDto().getId()).orElse(null);
        if(companyControl == null){
            Company company = companyMapper.toCompany(companyDto);
            Users user = userRepository.findById(companyDto.getUserDto().getId()).orElse(null);
            if(user != null){
                company.setUser(user);
                return companyMapper.toCompanyDto(companyRepository.save(company));
            }
        }
        return null;
    }

    @Override
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Company company = companyMapper.toCompany(companyDto);
        Company companyToUptade = companyRepository.getOne(id);
        companyToUptade.setAddress(company.getAddress());
        companyToUptade.setName(company.getName());
        companyToUptade.setPhone(company.getPhone());
        companyToUptade.setWebsite(company.getWebsite());
        companyToUptade.setDescription(company.getDescription());
        companyToUptade.setImage(company.getImage());
        return companyMapper.toCompanyDto(companyRepository.save(companyToUptade));
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}

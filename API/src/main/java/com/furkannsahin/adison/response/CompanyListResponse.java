package com.furkannsahin.adison.response;

import com.furkannsahin.adison.dto.CompanyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyListResponse {
    private List<CompanyDto> companyDtoList;
}

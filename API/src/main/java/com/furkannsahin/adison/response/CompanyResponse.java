package com.furkannsahin.adison.response;

import com.furkannsahin.adison.dto.CompanyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private CompanyDto companyDto;
}

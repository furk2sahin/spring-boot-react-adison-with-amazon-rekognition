package com.furkannsahin.adison.request;

import com.furkannsahin.adison.dto.CompanyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    private CompanyDto companyDto;
}

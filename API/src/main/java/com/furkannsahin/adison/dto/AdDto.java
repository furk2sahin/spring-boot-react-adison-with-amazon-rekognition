package com.furkannsahin.adison.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("postsDto")
public class AdDto extends BaseEntityDto{
    private Long id;
    private String requiredWord;
    private boolean expired;
    private CompanyDto companyDto;
    private List<UserAdPostDto> postsDto = new ArrayList<>();
}

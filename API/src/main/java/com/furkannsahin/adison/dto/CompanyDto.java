package com.furkannsahin.adison.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto extends BaseEntityDto{
    private Long id;
    private String name;
    private String address;
    private String website;
    private String phone;
    private List<AdDto> adsDto = new ArrayList<>();
    private UserDto userDto;
}

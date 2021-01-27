package com.furkannsahin.adison.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("adsDto")
public class CompanyDto extends BaseEntityDto{
    private Long id;
    private String name;
    private String address;
    private String website;
    private String phone;
    private List<AdDto> adsDto = new ArrayList<>();
    private UserDto userDto;
}

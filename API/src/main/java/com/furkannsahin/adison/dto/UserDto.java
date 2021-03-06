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
@JsonIgnoreProperties({"postsDto", "companyDto"})
public class UserDto extends BaseEntityDto{
    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private String userType;
    private boolean active;
    private CompanyDto companyDto;
    private List<UserAdPostDto> postsDto = new ArrayList<>();
}

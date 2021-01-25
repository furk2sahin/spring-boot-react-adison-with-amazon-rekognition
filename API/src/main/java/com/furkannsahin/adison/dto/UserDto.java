package com.furkannsahin.adison.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseEntityDto{
    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private String roles;
    private String userType;
    private boolean active;
    private CompanyDto companyDto;
    private List<UserAdPostDto> postsDto = new ArrayList<>();
}

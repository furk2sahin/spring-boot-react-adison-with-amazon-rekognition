package com.furkannsahin.adison.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"postsDto", "companyDto", "rolesDto"})
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
    private UserRoleDto rolesDto;
}

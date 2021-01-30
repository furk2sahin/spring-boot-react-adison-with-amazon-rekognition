package com.furkannsahin.adison.dto;

import com.furkannsahin.adison.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    private Long id;
    private String name;
    private User userDto;
}

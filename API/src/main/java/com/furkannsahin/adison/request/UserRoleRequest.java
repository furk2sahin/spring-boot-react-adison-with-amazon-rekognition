package com.furkannsahin.adison.request;

import com.furkannsahin.adison.dto.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequest {
    private UserRoleDto userRoleDto;
}

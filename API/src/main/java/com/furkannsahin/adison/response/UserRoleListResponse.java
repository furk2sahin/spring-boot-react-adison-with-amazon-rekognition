package com.furkannsahin.adison.response;

import com.furkannsahin.adison.dto.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleListResponse {
    private List<UserRoleDto> userRoleDtoList;
}

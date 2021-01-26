package com.furkannsahin.adison.response;

import com.furkannsahin.adison.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListResponse {
    private List<UserDto> userDtoList;
}

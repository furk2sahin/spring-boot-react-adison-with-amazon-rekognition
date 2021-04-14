package com.furkannsahin.adison.response;

import com.furkannsahin.adison.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UserDto userDto;
}

package com.furkannsahin.adison.request;

import com.furkannsahin.adison.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private UserDto userDto;
}

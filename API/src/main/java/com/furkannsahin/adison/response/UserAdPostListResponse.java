package com.furkannsahin.adison.response;

import com.furkannsahin.adison.dto.UserAdPostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAdPostListResponse {
    private List<UserAdPostDto> userAdPostDtoList;
}

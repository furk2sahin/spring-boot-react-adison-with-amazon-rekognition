package com.furkannsahin.adison.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdPostDto extends BaseEntityDto {
    private Long id;
    private boolean accepted;
    private boolean active;
    private AdDto adDto;
    private UserDto userDto;
}

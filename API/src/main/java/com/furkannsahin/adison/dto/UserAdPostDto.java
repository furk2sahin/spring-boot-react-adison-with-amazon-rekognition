package com.furkannsahin.adison.dto;

import lombok.*;

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

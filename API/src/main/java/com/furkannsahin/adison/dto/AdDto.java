package com.furkannsahin.adison.dto;

import com.furkannsahin.adison.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDto extends BaseEntityDto{
    private Long id;
    private String requiredWord;
    private boolean expired;
    private Company company;
    private List<UserAdPostDto> postsDto = new ArrayList<>();
}

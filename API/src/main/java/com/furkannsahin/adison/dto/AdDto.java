package com.furkannsahin.adison.dto;

import com.furkannsahin.adison.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

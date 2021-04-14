package com.furkannsahin.adison.response;

import com.furkannsahin.adison.dto.AdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdListResponse {
    private List<AdDto> adDtoList;
}

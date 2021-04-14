package com.furkannsahin.adison.response;

import com.furkannsahin.adison.dto.AdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdResponse {
    private AdDto adDto;
}

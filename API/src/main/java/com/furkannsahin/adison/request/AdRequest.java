package com.furkannsahin.adison.request;

import com.furkannsahin.adison.dto.AdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdRequest {
    private AdDto adDto;
}

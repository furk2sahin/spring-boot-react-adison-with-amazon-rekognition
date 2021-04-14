package com.furkannsahin.adison.service;

import com.furkannsahin.adison.dto.AdDto;

import java.util.List;

public interface AdService {
    List<AdDto> getAllAds();
    AdDto getAdById(Long id);
    AdDto addAd(AdDto adDto);
    AdDto updateAd(Long id, AdDto adDto);
    void deleteAd(Long id);
}

package com.furkannsahin.adison.service.impl;

import com.furkannsahin.adison.dto.AdDto;
import com.furkannsahin.adison.mapper.AdMapper;
import com.furkannsahin.adison.model.Ad;
import com.furkannsahin.adison.repository.AdRepository;
import com.furkannsahin.adison.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdMapper adMapper;
    private final AdRepository adRepository;

    @Override
    public List<AdDto> getAllAds() {
        return adMapper.toAdDtos(adRepository.findAll());
    }

    @Override
    public AdDto getAdById(Long id) {
        return adMapper.toAdDto(adRepository.getOne(id));
    }

    @Override
    public AdDto addAd(AdDto adDto) {
        Ad ad = adMapper.toAd(adDto);
        return adMapper.toAdDto(adRepository.save(ad));
    }

    @Override
    public AdDto updateAd(Long id, AdDto adDto) {
        Ad ad = adMapper.toAd(adDto);
        Ad adToUpdate = adRepository.getOne(id);
        adToUpdate.setExpired(ad.isExpired());
        adToUpdate.setRequiredWord(ad.getRequiredWord());
        adToUpdate.setDescription(ad.getDescription());
        adToUpdate.setImage(ad.getImage());
        return adMapper.toAdDto(adRepository.save(adToUpdate));
    }

    @Override
    public void deleteAd(Long id) {
        adRepository.deleteById(id);
    }
}

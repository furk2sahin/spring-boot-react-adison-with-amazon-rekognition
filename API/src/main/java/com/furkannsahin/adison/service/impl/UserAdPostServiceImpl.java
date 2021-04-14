package com.furkannsahin.adison.service.impl;

import com.furkannsahin.adison.dto.UserAdPostDto;
import com.furkannsahin.adison.mapper.UserAdPostMapper;
import com.furkannsahin.adison.model.Ad;
import com.furkannsahin.adison.model.UserAdPost;
import com.furkannsahin.adison.model.Users;
import com.furkannsahin.adison.repository.AdRepository;
import com.furkannsahin.adison.repository.UserAdPostRepository;
import com.furkannsahin.adison.repository.UserRepository;
import com.furkannsahin.adison.service.UserAdPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdPostServiceImpl implements UserAdPostService {

    private final UserAdPostRepository userAdPostRepository;
    private final UserAdPostMapper userAdPostMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserAdPostDto> getAllUserAdPosts() {
        return userAdPostMapper.toUserAdPostDtos(userAdPostRepository.findAll());
    }

    @Override
    public UserAdPostDto getUserAdPostById(Long id) {
        return userAdPostMapper.toUserAdPostDto(userAdPostRepository.getOne(id));
    }

    @Override
    public UserAdPostDto addUserAdPost(UserAdPostDto userAdPostDto) {
        UserAdPost userAdPost = userAdPostMapper.toUserAdPost(userAdPostDto);
        Users user = userRepository.findById(userAdPostDto.getUserDto().getId()).orElse(null);
        Ad ad = adRepository.findById(userAdPostDto.getAdDto().getId()).orElse(null);
        if(user != null && ad != null){
            userAdPost.setUser(user);
            userAdPost.setAd(ad);
            return userAdPostMapper.toUserAdPostDto(userAdPostRepository.save(userAdPost));
        }
        return null;
    }

    @Override
    public UserAdPostDto updateUserAdPost(Long id, UserAdPostDto userAdPostDto) {
        UserAdPost userAdPost = userAdPostMapper.toUserAdPost(userAdPostDto);
        UserAdPost userToUpdate = userAdPostRepository.getOne(id);
        userToUpdate.setAccepted(userAdPost.isAccepted());
        userToUpdate.setActive(userAdPost.isActive());
        userToUpdate.setDescription(userAdPost.getDescription());
        userToUpdate.setImage(userAdPost.getImage());
        return userAdPostMapper.toUserAdPostDto(userAdPostRepository.save(userToUpdate));
    }

    @Override
    public void deleteUserAdPost(Long id) {
        userAdPostRepository.deleteById(id);
    }
}

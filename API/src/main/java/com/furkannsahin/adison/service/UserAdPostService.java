package com.furkannsahin.adison.service;

import com.furkannsahin.adison.dto.UserAdPostDto;

import java.util.List;

public interface UserAdPostService {
    List<UserAdPostDto> getAllUserAdPosts();
    UserAdPostDto getUserAdPostById(Long id);
    UserAdPostDto addUserAdPost(UserAdPostDto userAdPostDto);
    UserAdPostDto updateUserAdPost(Long id, UserAdPostDto userAdPostDto);
    void deleteUserAdPost(Long id);
}

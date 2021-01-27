package com.furkannsahin.adison.controller;

import com.furkannsahin.adison.dto.UserAdPostDto;
import com.furkannsahin.adison.request.UserAdPostRequest;
import com.furkannsahin.adison.response.UserAdPostListResponse;
import com.furkannsahin.adison.response.UserAdPostResponse;
import com.furkannsahin.adison.service.UserAdPostService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserAdPostRestController {

    private UserAdPostService userAdPostService;

    @Autowired
    public void setUserAdPostService(UserAdPostService userAdPostService) {
        this.userAdPostService = userAdPostService;
    }

    @GetMapping("/posts")
    public ResponseEntity<UserAdPostListResponse> getAllCompanies(){
        return ResponseEntity.ok(new UserAdPostListResponse(userAdPostService.getAllUserAdPosts()));
    }

    @PostMapping("/post")
    public ResponseEntity<UserAdPostResponse> createCompany(@RequestBody UserAdPostRequest userAdPostRequest){
        try{
            UserAdPostResponse userAdPostResponse = new UserAdPostResponse(userAdPostService.addUserAdPost(userAdPostRequest.getUserAdPostDto()));
            return ResponseEntity.ok(userAdPostResponse);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<UserAdPostResponse> updateCompany(@PathVariable("id") Long id, @RequestBody UserAdPostRequest userAdPostRequest){
        try{
            UserAdPostResponse userAdPostResponse = new UserAdPostResponse(userAdPostService.updateUserAdPost(id, userAdPostRequest.getUserAdPostDto()));
            return ResponseEntity.ok(userAdPostResponse);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long id){
        try{
            userAdPostService.deleteUserAdPost(id);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>("Post Successfully deleted by id: " + id, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<UserAdPostResponse> getCompanyById(@PathVariable("id") Long id){
        try{
            UserAdPostDto userAdPostDto = userAdPostService.getUserAdPostById(id);
            return ResponseEntity.ok(new UserAdPostResponse(userAdPostDto));
        } catch (Exception e) {
            return ResponseEntity.ok(new UserAdPostResponse(null));
        }
    }
}

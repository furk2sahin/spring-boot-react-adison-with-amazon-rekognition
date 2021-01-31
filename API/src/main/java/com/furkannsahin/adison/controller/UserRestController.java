package com.furkannsahin.adison.controller;

import com.furkannsahin.adison.dto.UserDto;
import com.furkannsahin.adison.request.UserRequest;
import com.furkannsahin.adison.response.UserListResponse;
import com.furkannsahin.adison.response.UserResponse;
import com.furkannsahin.adison.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<UserListResponse> getAllUsers(){
        return ResponseEntity.ok(new UserListResponse(userService.getAllUsers()));
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try{
            String cryptedPassword = passwordEncoder.encode(userRequest.getUserDto().getPassword());
            userRequest.getUserDto().setPassword(cryptedPassword);
            UserResponse userResponse = new UserResponse(userService.addUser(userRequest.getUserDto()));
            return ResponseEntity.ok(userResponse);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest){
        try{
            UserResponse userResponse = new UserResponse(userService.updateUser(id, userRequest.getUserDto()));
            return ResponseEntity.ok(userResponse);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        try{
            userService.deleteUser(id);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>("User Successfully deleted by id: " + id, HttpStatus.OK);
    }

    @GetMapping("/user/id/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){
        try{
           UserDto userDto = userService.getUserById(id);
           return ResponseEntity.ok(new UserResponse(userDto));
        } catch (Exception e) {
            return ResponseEntity.ok(new UserResponse(null));
        }
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<UserResponse> getUserByUserName(@PathVariable("username") String userName){
        try{
            UserDto userDto = userService.getUserByUserName(userName);
            return ResponseEntity.ok(new UserResponse(userDto));
        } catch (Exception e){
            return ResponseEntity.ok(new UserResponse(null));
        }
    }
}

package com.furkannsahin.adison.controller;

import com.furkannsahin.adison.dto.UserRoleDto;
import com.furkannsahin.adison.response.UserRoleListResponse;
import com.furkannsahin.adison.response.UserRoleResponse;
import com.furkannsahin.adison.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;

    @GetMapping("/user_roles")
    public ResponseEntity<UserRoleListResponse> getAllRoles(){
        return ResponseEntity.ok(new UserRoleListResponse(userRoleService.getAllUserRoles()));
    }

    @PostMapping("/user_role")
    public ResponseEntity<UserRoleResponse> createRole(@RequestBody UserRoleDto roleDto){
        try{
            UserRoleDto userRoleDto = userRoleService.addUserRole(roleDto);
            return ResponseEntity.ok(new UserRoleResponse(userRoleDto));
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/user_role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id){
        try{
            userRoleService.deleteUserRole(id);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>("Role Successfully deleted by id: " + id, HttpStatus.OK);
    }

    @GetMapping("/user_role/{id}")
    public ResponseEntity<UserRoleListResponse> getUserRoles(@PathVariable("id") Long id){
            List<UserRoleDto> userRolesDto = userRoleService.getUserRoleByUserId(id);
            return ResponseEntity.ok(new UserRoleListResponse(userRolesDto));
    }
}

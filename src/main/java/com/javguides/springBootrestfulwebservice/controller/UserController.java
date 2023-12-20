package com.javguides.springBootrestfulwebservice.controller;

import com.javguides.springBootrestfulwebservice.dto.UserDto;
import com.javguides.springBootrestfulwebservice.exception.EmailAlreadyExistsException;
import com.javguides.springBootrestfulwebservice.exception.ErrorDetails;
import com.javguides.springBootrestfulwebservice.exception.ResourceNotFoundException;
import com.javguides.springBootrestfulwebservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )

    // build crete user Rest API
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto user1 = userService.createUsers(userDto);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get User By ID REST API",
            description = "Get User By ID REST API is used to get a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/userById/{id}")
   public ResponseEntity<UserDto> getUserById(@PathVariable("id")Long userId){
        UserDto userById = userService.getUserById(userId);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }
    @Operation(
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get a all the users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )

     @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> allUser = userService.getAllUser();
        return new ResponseEntity<List<UserDto>>(allUser,HttpStatus.OK);
    }
    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("/updateUser/{id}")
    public UserDto updateUserByIds(@RequestBody @Valid UserDto userDto, @PathVariable("id") Long userId) {
        UserDto updateUser = userService.updateUserById(userDto, userId);
      return updateUser;

    }
    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByUserId(@PathVariable("id") Long userId){
        userService.deleteUserById(userId);
        return  new ResponseEntity<>("user deleted successfully ",HttpStatus.OK);
    }

}

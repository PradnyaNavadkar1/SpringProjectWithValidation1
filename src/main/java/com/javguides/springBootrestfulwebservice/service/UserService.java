package com.javguides.springBootrestfulwebservice.service;

import com.javguides.springBootrestfulwebservice.dto.UserDto;
import com.javguides.springBootrestfulwebservice.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUsers(UserDto userDto);
    UserDto getUserById(Long userId);


    List<UserDto> getAllUser();
    UserDto updateUserById(UserDto userDto,Long userId);
    void deleteUserById(Long userId);
}

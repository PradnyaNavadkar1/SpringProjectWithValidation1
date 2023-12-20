package com.javguides.springBootrestfulwebservice.mapper;

import com.javguides.springBootrestfulwebservice.dto.UserDto;
import com.javguides.springBootrestfulwebservice.entity.User;

public class UserMapper {

    //convert JPA entity into the UserDto
    public static UserDto maptoUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;

    }


        //convert UserDto into the Jpa Entity


        public static User DtoToUser(UserDto userDto){
            User user1=new User(
                    userDto.getId(),
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getEmail()
            );
            return user1;

        }
    }







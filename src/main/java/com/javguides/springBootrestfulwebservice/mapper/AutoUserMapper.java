package com.javguides.springBootrestfulwebservice.mapper;

import com.javguides.springBootrestfulwebservice.dto.UserDto;
import com.javguides.springBootrestfulwebservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER= Mappers.getMapper(AutoUserMapper.class);
    //@Mapping(source = "email",target = "emailAddress")
    UserDto maptoUserDto(User user);
    User userDtoToUser(UserDto userDto);
}

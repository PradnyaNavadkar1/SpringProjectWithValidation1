package com.javguides.springBootrestfulwebservice.serviceImpl;

import com.javguides.springBootrestfulwebservice.dto.UserDto;
import com.javguides.springBootrestfulwebservice.entity.User;
import com.javguides.springBootrestfulwebservice.exception.EmailAlreadyExistsException;
import com.javguides.springBootrestfulwebservice.exception.ErrorDetails;
import com.javguides.springBootrestfulwebservice.exception.ResourceNotFoundException;
import com.javguides.springBootrestfulwebservice.mapper.AutoUserMapper;
import com.javguides.springBootrestfulwebservice.mapper.UserMapper;
import com.javguides.springBootrestfulwebservice.repositories.UserRepository;
import com.javguides.springBootrestfulwebservice.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUsers(UserDto userDto) {

//        User user=UserMapper.dtoToUser(userDto);

        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        if(byEmail.isPresent()){
            throw new EmailAlreadyExistsException("Email already registered here!");
        }


        User user=modelMapper.map(userDto,User.class);

  /*      //Convert UserDto into user JPA Entity
       User user=new User(
               userDto.getId(),
           userDto.getFirstName(),
           userDto.getLastName(),
           userDto.getEmail()

       );*/
       // User user= AutoUserMapper.MAPPER.userDtoToUser(userDto);
        User savedUser=userRepository.save(user);
     /*    //Convert user jpa entity into userDto
        UserDto userDto1=new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
       return userDto1;*/

//        UserDto saveduserDto = UserMapper.mapToDto(savedUser);
        UserDto saveduserDto = modelMapper.map(savedUser,UserDto.class);
     //   UserDto saveduserDto = AutoUserMapper.MAPPER.maptoUserDto(savedUser);
        return saveduserDto;

    }

    @Override
    public UserDto getUserById(Long  userId) {
     //   Optional<User> byId = userRepository.findById(userId);
//        User user = byId.get();
        //return UserMapper.mapToDto(user);
      //  return modelMapper.map(user,UserDto.class);
        /*if(byId.isPresent()){
            System.out.println(byId.get());
        }else {
            System.out.println("there is null value");
        }
        return byId.get();*/
        User byId = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","id",  userId));
//        return AutoUserMapper.MAPPER.maptoUserDto(byId.get());
        return AutoUserMapper.MAPPER.maptoUserDto(byId);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userAll = userRepository.findAll();
       // List<UserDto> collect = userAll.stream().map(UserMapper::mapToDto).collect(Collectors.toList());
//        List<UserDto> users = userAll.stream().map((user)->modelMapper.map(user,UserDto.class))
//                .collect(Collectors.toList());
        List<UserDto> users = userAll.stream().map((user)->AutoUserMapper.MAPPER.maptoUserDto(user))
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public UserDto updateUserById(UserDto userDto, Long userId) {
        User optionalUser = userRepository.findById(userDto.getId()).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));

      optionalUser.setFirstName(userDto.getFirstName());
      optionalUser.setLastName(userDto.getLastName());
      optionalUser.setEmail(userDto.getEmail());
        User user = userRepository.save(optionalUser);
       // return UserMapper.mapToDto(user);
//        return modelMapper.map(user,UserDto.class);
        return AutoUserMapper.MAPPER.maptoUserDto(user);
    }

    @Override
    public void deleteUserById( Long userId) {
        User optionalUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        userRepository.deleteById(userId);

    }

    }





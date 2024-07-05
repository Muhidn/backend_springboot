package com.Garbage.projectColection.controller;

import com.Garbage.projectColection.model.UserEntity;
import com.Garbage.projectColection.model.dto.UserDto;
import com.Garbage.projectColection.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;
    public UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @PostMapping(path = "/insert")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        UserEntity savedUser = userService.saveUser(userEntity);
        return new ResponseEntity<>(modelMapper.map(savedUser, UserDto.class), HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserEntity> userEntity = userService.getAllUsers();
        List<UserDto> userDto = userEntity.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable("userID") Long userID) {
        Optional<UserEntity> userEntity = userService.getUserById(userID);
        return userEntity.map(entity -> new ResponseEntity<>(modelMapper.map(entity, UserDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userID, @RequestBody UserDto userDto) {
        if (!userService.isExist(userID)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userDto.setUserID(userID); // Ensure UserDto has a setId method
            UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
            UserEntity savedUser = userService.saveUser(userEntity);
            return new ResponseEntity<>(modelMapper.map(savedUser, UserDto.class), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long userID) {
        userService.deleteUser(userID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

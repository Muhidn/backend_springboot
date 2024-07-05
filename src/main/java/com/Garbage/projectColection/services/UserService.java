package com.Garbage.projectColection.services;

import com.Garbage.projectColection.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUserById(Long userID);
    void deleteUser(Long userID);
    boolean isExist(Long userID);
}

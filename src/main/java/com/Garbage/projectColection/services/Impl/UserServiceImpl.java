package com.Garbage.projectColection.services.Impl;

import com.Garbage.projectColection.Repository.UserRepository;
import com.Garbage.projectColection.model.UserEntity;
import com.Garbage.projectColection.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long userID) {
        userRepository.deleteById(userID);
    }

    @Override
    public boolean isExist(Long userID) {
        return userRepository.existsById(userID);
    }
}

package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.dataTransferObject.SQL.UserEntityCreateDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.UserEntityReadDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.UserEntityUpdateDTO;
import com.jlgdev.ceres.models.entities.UserEntity;
import com.jlgdev.ceres.models.mapper.UserEntityMapper;
import com.jlgdev.ceres.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityMapper userMapper;

    public UserEntityReadDTO createUser(UserEntityCreateDTO userCreateDTO) {
        UserEntity user = userMapper.toCreateEntity(userCreateDTO);
        return userMapper.toReadDTO(userRepository.save(user));
    }

    public Optional<UserEntityReadDTO> readUser(Long  id) {
        return userRepository.findById(id).map(userMapper::toReadDTO);
    }

    public Optional<UserEntityReadDTO> readUser(String email) {
        return userRepository.findByEmail(email).map(userMapper::toReadDTO);
    }

    public UserEntityReadDTO updateUser(Long  id, UserEntityUpdateDTO userUpdateDTO) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        userMapper.updateEntityFromDTO(userUpdateDTO, user);
        return userMapper.toReadDTO(userRepository.save(user));
    }

    public void deleteUser(Long  id) {
        userRepository.deleteById(id);
    }
}

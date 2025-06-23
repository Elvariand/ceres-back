package com.jlgdev.ceres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlgdev.ceres.models.UserEntity;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);

}

package com.jlgdev.ceres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlgdev.ceres.models.entities.UserEntity;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long > {

    Optional<UserEntity> findByEmail(String email);
    // Optional<UserEntity> findById(String email);
    Boolean existsByEmail(String email);

}

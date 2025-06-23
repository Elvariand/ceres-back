package com.jlgdev.ceres.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlgdev.ceres.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}

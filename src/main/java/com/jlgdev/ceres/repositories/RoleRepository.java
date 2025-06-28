package com.jlgdev.ceres.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlgdev.ceres.models.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long > {

    Optional<Role> findByName(String name);
}

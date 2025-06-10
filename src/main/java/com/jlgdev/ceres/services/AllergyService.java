package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.dataAccessObject.AllergyDAO;
import com.jlgdev.ceres.repositories.AllergyRepository;

@Service
public class AllergyService {

    @Autowired
    private AllergyRepository allergyRepository;

    public Optional<AllergyDAO> getAllergyById(String id) {
        return allergyRepository.findById(id);
    }

    public Iterable<AllergyDAO> getAllAllergies() {
        return allergyRepository.findAll();
    }

    public AllergyDAO save(AllergyDAO Recipe) {
        return allergyRepository.save(Recipe);
    }
}

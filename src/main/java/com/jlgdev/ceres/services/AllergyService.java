package com.jlgdev.ceres.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Optional<AllergyDAO> getAllergyByName(String name) {
        return allergyRepository.findByName(name);
    }

    public Iterable<AllergyDAO> getAllAllergies() {
        List<AllergyDAO> result = allergyRepository.findAll();
        result.remove(result.getLast());
        result.remove(result.getLast());
        return result;
    }

    public Iterable<AllergyDAO> getAllRestrictions() {
        return allergyRepository.findAll();
    }

    public Set<AllergyDAO> getReligions() {
        Set<AllergyDAO> result = new HashSet<>();
        result.add(getAllergyByName("judaism").get());
        result.add(getAllergyByName("islam").get());

        return result;
    }

    public AllergyDAO save(AllergyDAO Recipe) {
        return allergyRepository.save(Recipe);
    }
}

package com.jlgdev.ceres.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.dataTransferObject.NoSQL.AllergyDTO;
import com.jlgdev.ceres.repositories.AllergyRepository;

@Service
public class AllergyService {

    @Autowired
    private AllergyRepository allergyRepository;

    public Optional<AllergyDTO> getAllergyById(String id) {
        return allergyRepository.findById(id);
    }
    public Optional<AllergyDTO> getAllergyByName(String name) {
        return allergyRepository.findByName(name);
    }

    public Iterable<AllergyDTO> getAllAllergies() {
        List<AllergyDTO> result = allergyRepository.findAll();
        result.remove(result.getLast());
        result.remove(result.getLast());
        return result;
    }

    public Iterable<AllergyDTO> getAllRestrictions() {
        return allergyRepository.findAll();
    }

    public Set<AllergyDTO> getReligions() {
        Set<AllergyDTO> result = new HashSet<>();
        result.add(getAllergyByName("judaism").get());
        result.add(getAllergyByName("islam").get());

        return result;
    }

    public AllergyDTO save(AllergyDTO Recipe) {
        return allergyRepository.save(Recipe);
    }
}

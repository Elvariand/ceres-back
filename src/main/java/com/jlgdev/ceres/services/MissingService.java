package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.dataTransferObject.NoSQL.MissingIngredientsDTO;
import com.jlgdev.ceres.repositories.MissingRepository;

@Service
public class MissingService {

    @Autowired
    private MissingRepository missingRepository;

    public Optional<MissingIngredientsDTO> getMissingById(int id) {
        return missingRepository.findById(id);
    }

    public MissingIngredientsDTO save(MissingIngredientsDTO Recipe) {
        return missingRepository.save(Recipe);
    }
}

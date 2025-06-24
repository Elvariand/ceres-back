package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.dataTransferObject.AlimentDTO;
import com.jlgdev.ceres.repositories.AlimentRepository;

@Service
public class AlimentService {

    @Autowired
    private AlimentRepository alimentRepository;

    public Iterable<AlimentDTO> getAllAliments() {
        return alimentRepository.findAll();
    }

    public Iterable<AlimentDTO> getAllVeganAliments() {
        return alimentRepository.findAllVegan();
    }

    public Iterable<AlimentDTO> getAllNonVeganAliments() {
        return alimentRepository.findAllNonVegan();
    }

    public Iterable<AlimentDTO> getAllVegetarianAliments() {
        return alimentRepository.findAllVegetarian();
    }

    public Iterable<AlimentDTO> getAllNonVegetarianAliments() {
        return alimentRepository.findAllNonVegetarian();
    }

    public Optional<AlimentDTO> getAlimentById(String id) {
        return alimentRepository.findById(id);
    }

    public Iterable<AlimentDTO> getAlimentByNameEn(String nameEn) {
        return alimentRepository.findByNameEn(nameEn);
    }

    public Iterable<AlimentDTO> getAlimentByNameEnContaining(String nameEn) {
        return alimentRepository.findByNameEnContaining(nameEn);
    }

    public Iterable<AlimentDTO> getAlimentByAisle(String aisle) {
        return alimentRepository.findByAisle(aisle);
    }

    public Iterable<AlimentDTO> getAlimentByAisleContaining(String aisle) {
        return alimentRepository.findByAisleContaining(aisle);
    }

    public Iterable<AlimentDTO> getAlimentByCategoryPath(String categoryPath) {
        return alimentRepository.findByCategoryPath(categoryPath);
    }

    public Iterable<AlimentDTO> getAlimentByCategoryPathContaining(String categoryPath) {
        return alimentRepository.findByCategoryPathContaining(categoryPath);
    }

    public Iterable<AlimentDTO> getAlimentMissingPath() {
        return alimentRepository.findAllMissingPath();
    }

    public AlimentDTO save(AlimentDTO aliment) {
        return alimentRepository.save(aliment);
    }
}

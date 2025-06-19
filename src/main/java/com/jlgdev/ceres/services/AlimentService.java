package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.dataAccessObject.AlimentDAO;
import com.jlgdev.ceres.repositories.AlimentRepository;

@Service
public class AlimentService {

    @Autowired
    private AlimentRepository alimentRepository;

    public Iterable<AlimentDAO> getAllAliments() {
        return alimentRepository.findAll();
    }

    public Iterable<AlimentDAO> getAllVeganAliments() {
        return alimentRepository.findAllVegan();
    }

    public Iterable<AlimentDAO> getAllNonVeganAliments() {
        return alimentRepository.findAllNonVegan();
    }

    public Iterable<AlimentDAO> getAllVegetarianAliments() {
        return alimentRepository.findAllVegetarian();
    }

    public Iterable<AlimentDAO> getAllNonVegetarianAliments() {
        return alimentRepository.findAllNonVegetarian();
    }

    public Optional<AlimentDAO> getAlimentById(String id) {
        return alimentRepository.findById(id);
    }

    public Iterable<AlimentDAO> getAlimentByNameEn(String nameEn) {
        return alimentRepository.findByNameEn(nameEn);
    }

    public Iterable<AlimentDAO> getAlimentByNameEnContaining(String nameEn) {
        return alimentRepository.findByNameEnContaining(nameEn);
    }

    public Iterable<AlimentDAO> getAlimentByAisle(String aisle) {
        return alimentRepository.findByAisle(aisle);
    }

    public Iterable<AlimentDAO> getAlimentByAisleContaining(String aisle) {
        return alimentRepository.findByAisleContaining(aisle);
    }

    public Iterable<AlimentDAO> getAlimentByCategoryPath(String categoryPath) {
        return alimentRepository.findByCategoryPath(categoryPath);
    }

    public Iterable<AlimentDAO> getAlimentByCategoryPathContaining(String categoryPath) {
        return alimentRepository.findByCategoryPathContaining(categoryPath);
    }

    public Iterable<AlimentDAO> getAlimentMissingPath() {
        return alimentRepository.findAllMissingPath();
    }

    public AlimentDAO save(AlimentDAO aliment) {
        return alimentRepository.save(aliment);
    }
}

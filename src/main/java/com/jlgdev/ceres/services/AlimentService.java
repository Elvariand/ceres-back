package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.mongo.Aliment;
import com.jlgdev.ceres.repositories.AlimentRepository;

@Service
public class AlimentService {

    @Autowired
    private AlimentRepository alimentRepository;

    public Iterable<Aliment> getAllAliments() {
        return alimentRepository.findAll();
    }

    public Optional<Aliment> getAlimentById(String id) {
        return alimentRepository.findById(id);
    }

    public Iterable<Aliment> getAlimentByNameEn(String nameEn) {
        return alimentRepository.findByNameEn(nameEn);
    }

    public Iterable<Aliment> getAlimentByNameEnContaining(String nameEn) {
        return alimentRepository.findByNameEnContaining(nameEn);
    }

    public Iterable<Aliment> getAlimentByAisle(String aisle) {
        return alimentRepository.findByAisle(aisle);
    }

    public Iterable<Aliment> getAlimentByAisleContaining(String aisle) {
        return alimentRepository.findByAisleContaining(aisle);
    }

    public Iterable<Aliment> getAlimentByCategoryPath(String categoryPath) {
        return alimentRepository.findByCategoryPath(categoryPath);
    }

    public Iterable<Aliment> getAlimentByCategoryPathContaining(String categoryPath) {
        return alimentRepository.findByCategoryPathContaining(categoryPath);
    }

    public Aliment save(Aliment aliment) {
        return alimentRepository.save(aliment);
    }
}

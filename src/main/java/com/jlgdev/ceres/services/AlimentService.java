package com.jlgdev.ceres.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.jsonToObject.AlimentJTO;
import com.jlgdev.ceres.repositories.AlimentRepository;

@Service
public class AlimentService {

    @Autowired
    private AlimentRepository alimentRepository;

    public Iterable<AlimentJTO> getAllAliments() {
        return alimentRepository.findAll();
    }

    public Optional<AlimentJTO> getAlimentById(String id) {
        return alimentRepository.findById(id);
    }

    public Iterable<AlimentJTO> getAlimentByNameEn(String nameEn) {
        return alimentRepository.findByNameEn(nameEn);
    }

    public Iterable<AlimentJTO> getAlimentByNameEnContaining(String nameEn) {
        return alimentRepository.findByNameEnContaining(nameEn);
    }

    public Iterable<AlimentJTO> getAlimentByAisle(String aisle) {
        return alimentRepository.findByAisle(aisle);
    }

    public Iterable<AlimentJTO> getAlimentByAisleContaining(String aisle) {
        return alimentRepository.findByAisleContaining(aisle);
    }

    public Iterable<AlimentJTO> getAlimentByCategoryPath(String categoryPath) {
        return alimentRepository.findByCategoryPath(categoryPath);
    }

    public Iterable<AlimentJTO> getAlimentByCategoryPathContaining(String categoryPath) {
        return alimentRepository.findByCategoryPathContaining(categoryPath);
    }

    public AlimentJTO save(AlimentJTO aliment) {
        return alimentRepository.save(aliment);
    }
}

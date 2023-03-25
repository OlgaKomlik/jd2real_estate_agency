package com.komlik.service.real_estate;

import com.komlik.domain.RealEstate;
import com.komlik.repository.real_estate.RealEstateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealEstateServiceImpl implements RealEstateService {

    private final RealEstateRepository repository;

    public RealEstateServiceImpl(RealEstateRepository repository) {
        this.repository = repository;
    }

    @Override
    public RealEstate findById(Long id) {
        return null;
    }

    @Override
    public Optional<RealEstate> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public RealEstate findByLastId() {
        return null;
    }

    @Override
    public List<RealEstate> findAll() {
        return repository.findAll();
    }

    @Override
    public RealEstate create(RealEstate object) {
        return null;
    }

    @Override
    public RealEstate update(RealEstate object) {
        return null;
    }

    @Override
    public RealEstate delete(Long id) {
        return null;
    }

    @Override
    public void hardDelete(Long id) {

    }

    @Override
    public List<RealEstate> searchRealEstate(String city, String type, Integer square) {
        return null;
    }
}

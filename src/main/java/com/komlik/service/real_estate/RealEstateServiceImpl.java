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
        return repository.findById(id);
    }

    @Override
    public Optional<RealEstate> findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public RealEstate findByLastId() {
        return repository.findByLastId();
    }

    @Override
    public List<RealEstate> findAll() {
        return repository.findAll();
    }

    @Override
    public RealEstate create(RealEstate object) {
        return repository.create(object);
    }

    @Override
    public RealEstate update(RealEstate object) {
        return repository.update(object);
    }

    @Override
    public RealEstate delete(Long id) {
        return repository.delete(id);
    }

    @Override
    public Optional<RealEstate> hardDelete(Long id) {
        return repository.hardDelete(id);
    }

    @Override
    public RealEstate changeOwnerPersonsId(Long realEstateId, Long newOwnerPersonsId) {
        return repository.changeOwnerPersonsId(realEstateId, newOwnerPersonsId);
    }

    @Override
    public Integer findBigestFlatSquare(int rooms) {
        return repository.findBigestFlatSquare(rooms);
    }

    @Override
    public Integer findSmallFlatSquare(int rooms) {
        return repository.findSmallFlatSquare(rooms);
    }

    @Override
    public List<RealEstate> searchRealEstate(String city, String type, Integer square) {
        return repository.searchRealEstate(city.toLowerCase(), type, square);
    }

    @Override
    public List<RealEstate> findBigestFlat(int rooms) {
        return repository.findBigestFlat(rooms);
    }

    @Override
    public List<RealEstate> findSmallFlat(int rooms) {
        return repository.findSmallFlat(rooms);
    }
}

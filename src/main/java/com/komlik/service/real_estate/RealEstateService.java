package com.komlik.service.real_estate;

import com.komlik.domain.RealEstate;

import java.util.List;
import java.util.Optional;


public interface RealEstateService{
    RealEstate findById(Long id);

    Optional<RealEstate> findOne(Long id);

    RealEstate findByLastId();

    List<RealEstate> findAll();

    RealEstate create(RealEstate object);

    RealEstate update(RealEstate object);

    RealEstate delete(Long id);

    Optional<RealEstate> hardDelete(Long id);

    List<RealEstate> searchRealEstate(String city, String type, Integer square);

    RealEstate changeOwnerPersonsId(Long realEstateId, Long newOwnerPersonsId);
    Integer findBigestFlatSquare(int rooms);
    Integer findSmallFlatSquare(int rooms);
    List<RealEstate> findBigestFlat(int rooms);

    List<RealEstate> findSmallFlat(int rooms);
}

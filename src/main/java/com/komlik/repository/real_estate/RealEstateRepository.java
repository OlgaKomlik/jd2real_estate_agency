package com.komlik.repository.real_estate;

import com.komlik.domain.RealEstate;
import com.komlik.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;

public interface RealEstateRepository extends CRUDRepository<Long, RealEstate> {

    Optional<RealEstate> hardDelete(Long id);
    List<RealEstate> searchRealEstate(String city, String type, Integer square);
    RealEstate changeOwnerPersonsId(Long realEstateId, Long newOwnerPersonsId);
    Integer findBigestFlatSquare(int rooms);
    Integer findSmallFlatSquare(int rooms);
    List<RealEstate> findBigestFlat(int rooms);

    List<RealEstate> findSmallFlat(int rooms);
}

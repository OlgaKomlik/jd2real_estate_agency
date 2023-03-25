package com.komlik.repository.real_estate;

import com.komlik.domain.RealEstate;
import com.komlik.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;

public interface RealEstateRepository extends CRUDRepository<Long, RealEstate> {

    List<RealEstate> searchRealEstate(String city, String type, Integer square);
}

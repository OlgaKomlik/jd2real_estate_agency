package com.komlik.controller.mvc;

import com.komlik.domain.RealEstate;
import com.komlik.service.real_estate.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/real-estates")
@RequiredArgsConstructor
public class RealEstateRestController {

    private final RealEstateService realEstateService;

    @GetMapping
    public ResponseEntity<Object> getAllRealEstates() {
        List<RealEstate> realEstates = realEstateService.findAll();
        return new ResponseEntity<>(realEstates, HttpStatus.OK);
    }
}

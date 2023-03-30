package com.komlik.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class RealEstateUpdateRequest {
    private Long id;
    private Integer square;
    private Integer rooms;
    private Integer floors;
    private Integer gardenSquare;
    private Boolean garage;
    private String address;
    private String city;
    private Long ownerPersonsId;
    private String type;
    private Long ownerCompaniesId;
    private String typeOwner;
    private Boolean isDeleted;
}

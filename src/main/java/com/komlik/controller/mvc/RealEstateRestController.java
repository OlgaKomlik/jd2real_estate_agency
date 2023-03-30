package com.komlik.controller.mvc;

import com.komlik.controller.requests.RealEstateCreateRequest;
import com.komlik.controller.requests.RealEstateUpdateRequest;
import com.komlik.domain.RealEstate;
import com.komlik.service.real_estate.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public ResponseEntity<RealEstate> createRealEstate(@RequestBody RealEstateCreateRequest request) {

        RealEstate build = RealEstate.builder()
                .square(request.getSquare())
                .rooms(request.getRooms())
                .floors(request.getFloors())
                .gardenSquare(request.getGardenSquare())
                .garage(request.getGarage())
                .address(request.getAddress())
                .city(request.getCity())
                .ownerPersonsId(request.getOwnerPersonsId())
                .type(request.getType())
                .ownerCompaniesId(request.getOwnerCompaniesId())
                .typeOwner(request.getTypeOwner())
                .build();
        RealEstate createdRealEstate = realEstateService.create(build);

        return new ResponseEntity<>(createdRealEstate, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RealEstate> findRealEstateById(@PathVariable Long id) {
        RealEstate realEstate = realEstateService.findById(id);

        return new ResponseEntity<>(realEstate, HttpStatus.OK);
    }

    @GetMapping("one/{id}")
    public ResponseEntity<Optional<RealEstate>> findOneRealEstate(@PathVariable Long id) {
        Optional<RealEstate> realEstate= realEstateService.findOne(id);

        return new ResponseEntity<>(realEstate, HttpStatus.OK);
    }

    @GetMapping("/last")
    public ResponseEntity<RealEstate> findLastRealEstate() {
        RealEstate realEstate= realEstateService.findByLastId();

        return new ResponseEntity<>(realEstate, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RealEstate> updateRealEstate(@RequestBody RealEstateUpdateRequest request) {
        RealEstate realEstate = realEstateService.update(RealEstate.builder()
                .id(request.getId())
                .square(request.getSquare())
                .rooms(request.getRooms())
                .floors(request.getFloors())
                .gardenSquare(request.getGardenSquare())
                .garage(request.getGarage())
                .address(request.getAddress())
                .city(request.getCity())
                .ownerPersonsId(request.getOwnerPersonsId())
                .type(request.getType())
                .ownerCompaniesId(request.getOwnerCompaniesId())
                .typeOwner(request.getTypeOwner())
                .isDeleted(request.getIsDeleted())
                .build());

        return new ResponseEntity<>(realEstate, HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<RealEstate> deleteRealEstate(@PathVariable Long id) {
        RealEstate realEstate = realEstateService.delete(id);

        return new ResponseEntity<>(realEstate, HttpStatus.OK);
    }

    @DeleteMapping("/hard-delete/{id}")
    public ResponseEntity<Optional<RealEstate>> hardDeleteRealEstate(@PathVariable Long id) {
        Optional<RealEstate> idDeleted = realEstateService.hardDelete(id);

        return new ResponseEntity<>(idDeleted, HttpStatus.OK);
    }

    //http://localhost:8080/rest/real-estates/search?city=minsk&type=flat&square=50
    @GetMapping("/search")
    public ResponseEntity<Object> searchRealEstate(@RequestParam("city") String city,
                                                       @RequestParam("type") String type,
                                                       @RequestParam("square") Integer square) {
        List<RealEstate> realEstates = realEstateService.searchRealEstate(city, type, square);

        return new ResponseEntity<>(realEstates, HttpStatus.OK);
    }

    @PatchMapping("/change/{id}owner{idSecond}")
    public ResponseEntity<RealEstate> changeOwnerPersonRealEstate(@PathVariable Long id, @PathVariable Long idSecond) {
        RealEstate realEstate = realEstateService.changeOwnerPersonsId(id, idSecond);

        return new ResponseEntity<>(realEstate, HttpStatus.OK);
    }

    @GetMapping("/big-square/{rooms}")
    public ResponseEntity<Integer> findBigestFlatSquare(@PathVariable Integer rooms) {
        Integer square = realEstateService.findBigestFlatSquare(rooms);

        return new ResponseEntity<>(square, HttpStatus.OK);
    }

    @GetMapping("/small-square/{rooms}")
    public ResponseEntity<Integer> findSmallFlatSquare(@PathVariable Integer rooms) {
        Integer square = realEstateService.findSmallFlatSquare(rooms);

        return new ResponseEntity<>(square, HttpStatus.OK);
    }

    @GetMapping("/big-flat/{rooms}")
    public ResponseEntity<Object> findBigestFlat(@PathVariable Integer rooms) {
        List<RealEstate> realEstates = realEstateService.findBigestFlat(rooms);

        return new ResponseEntity<>(realEstates, HttpStatus.OK);
    }

    @GetMapping("/small-flat/{rooms}")
    public ResponseEntity<Object> findSmallFlat(@PathVariable Integer rooms) {
        List<RealEstate> realEstates = realEstateService.findSmallFlat(rooms);

        return new ResponseEntity<>(realEstates, HttpStatus.OK);
    }
}

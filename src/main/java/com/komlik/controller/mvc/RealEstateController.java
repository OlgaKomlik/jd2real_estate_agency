package com.komlik.controller.mvc;

import com.komlik.controller.requests.SearchCriteria;
import com.komlik.domain.RealEstate;
import com.komlik.service.real_estate.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/real-estates")
public class RealEstateController {

    private static final Logger logger = Logger.getLogger(RealEstateController.class);

    private final RealEstateService realEstateService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findAllRealEstates() {
        List<RealEstate> realEstates = realEstateService.findAll();

        String collect = realEstates.stream().map(RealEstate::getAddress).collect(Collectors.joining(","));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("realEstateAddress", collect);
        modelAndView.addObject("realEstates", realEstates);

        modelAndView.setViewName("hello");

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView findRealEstateById(@PathVariable String id) {

        Long parsedRealEstateId;

        try {
            parsedRealEstateId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            logger.error("Real estate id: " + id + " cannot be parsed to Long", e);
            parsedRealEstateId = 1L;
        }

        RealEstate realEstate = realEstateService.findById(parsedRealEstateId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("realEstateAddress", realEstate.getAddress());
        modelAndView.addObject("realEstates", Collections.singletonList(realEstate));

        modelAndView.setViewName("hello");

        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchRealEstateByParams(@Valid @ModelAttribute SearchCriteria criteria, BindingResult result) {

        System.out.println(result);

        Integer parsedSquare;

        try {
            parsedSquare = Integer.parseInt(criteria.getSquare());
        } catch (NumberFormatException e) {
            logger.error("Real estate param square: " + criteria.getSquare() + " cannot be parsed to Integer", e);
            parsedSquare = 100;
        }

        List<RealEstate> searchList = realEstateService.searchRealEstate(criteria.getCity(), criteria.getType(), parsedSquare);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("realEstateAddress", "Search Result");
        modelAndView.addObject("realEstates", searchList);

        modelAndView.setViewName("hello");

        return modelAndView;
    }


}

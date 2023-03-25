package com.komlik.controller.mvc;

import com.komlik.domain.RealEstate;
import com.komlik.service.real_estate.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
}

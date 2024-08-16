package com.multi.spring2.city.controller;

import com.multi.spring2.city.domain.CityVO;
import com.multi.spring2.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/all")
    public String allCities(Model model) {
        List<CityVO> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return "city/all_result";
    }

    @PostMapping("/insert")
    public String insertCity(CityVO cityVO) {
        cityService.insertCity(cityVO);
        return "city/insert_result";
    }

}

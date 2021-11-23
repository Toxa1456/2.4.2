package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarController {

    @Autowired
    private CarServiceImpl carService;


    @GetMapping(value = "/cars")
    public String printCars(@RequestParam(value = "count") Optional<Byte> count, ModelMap model) {
        model.addAttribute("kars", carService.getListOfCars(count.orElse((byte) 0)));
        return "cars";
    }
}

package com.example.miniProject.Controller;

import java.util.List;

import com.example.miniProject.Model.Station;
import com.example.miniProject.Service.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/search")
    public ModelAndView getStationCrowd(@RequestParam String trainLine) {
        
        ModelAndView mvc = new ModelAndView();
        List<Station> sList = stationService.getTrainLine(trainLine);

        mvc.addObject("trainLine", trainLine);
        mvc.addObject("sList", sList);
        mvc.setViewName("result");

        return mvc;
        
    }

    

    // @GetMapping
    // public ModelAndView getCarpark() {
        
    //     ModelAndView mvc = new ModelAndView();
    //     List<Carpark> carparkList = cpServ.getAllCarparks();

    //     mvc.addObject("carparks", carparkList);
    //     mvc.setViewName("home");

    //     return mvc;
        
    // }

    // @PostMapping("/search")
    // public ModelAndView getAvailableLots(@RequestBody String development) {
    //     ModelAndView mav = new ModelAndView();
    //     List<Carpark> cList = cpServ.getAllCarparks();

    //     mav.addObject("lots", cList.get(0));
    //     mav.setViewName("result");
    //     return mav;
    // }

    // @PostMapping("/search")
    // public ModelAndView getAvailableLots(@RequestParam String development) {
    //     ModelAndView mav = new ModelAndView();
    //     return mav;
    // }
}

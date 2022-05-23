package com.example.miniProject.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.miniProject.Model.Station;
import com.example.miniProject.Service.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
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


    @GetMapping(path="/protected/favourites")
    public ModelAndView loginView(HttpSession sess){
        ModelAndView mav = new ModelAndView();
        String email = (String)sess.getAttribute("email");
       
        List<Station> favourites = stationService.getFavs(email);
        mav.addObject("email", email);
        mav.addObject("favourites", favourites);
        mav.setViewName("favourites");
        return mav;
    }


    @PostMapping(path="/addFav")
    public ModelAndView addFavourite(@RequestBody MultiValueMap<String, String> form, HttpSession session){
        ModelAndView mav = new ModelAndView();
        String email = (String)session.getAttribute("email");
        stationService.addFavourite(form, session);
        List<Station> favourites = stationService.getFavs(email);
        mav.addObject("favourites", favourites);
        mav.addObject("email", email);
        mav.setStatus(HttpStatus.OK);
        mav.setViewName("favourites");
        return mav;
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

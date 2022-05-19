package com.example.miniProject.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/protected/{view}")
public class ProtectedController {

    @GetMapping
    @PostMapping
    public ModelAndView post(@PathVariable String view, HttpSession sess) {
        String email = (String)sess.getAttribute("email");

        ModelAndView mav = new ModelAndView();
        mav.setViewName(view);
        mav.addObject("email", email);
        mav.setStatus(HttpStatus.OK);

        return mav;
    }
}

package com.example.miniProject.Controller;

import javax.servlet.http.HttpSession;

import com.example.miniProject.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService uSvc;

    @GetMapping("/logout")
    public String getLogout(HttpSession sess) {
        sess.invalidate();
        return "index";
    }

    @PostMapping 
    public ModelAndView userLogin(@RequestBody MultiValueMap<String,String> payload) {
        String email = payload.getFirst("email");
        String password = payload.getFirst("password");

        ModelAndView mvc = new ModelAndView();

        if (!uSvc.auth(email, password)) {
            mvc.addObject("email", email);
            mvc.setViewName("loginerror");
            mvc.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            mvc.addObject("email", email);
            mvc = new ModelAndView("redirect:/protected/favourites");
            // mvc.setViewName("home");
            // mvc.setStatus(HttpStatus.OK);
        }
        return mvc;
    }




}

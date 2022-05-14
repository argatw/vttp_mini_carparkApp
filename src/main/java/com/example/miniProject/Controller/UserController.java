package com.example.miniProject.Controller;

import com.example.miniProject.Model.User;
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
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService uSvc;

    @PostMapping ("/login")
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
            mvc.setViewName("home");
            mvc.setStatus(HttpStatus.OK);
        }
        return mvc;
    }

    @PostMapping("/createNewUser")
    public String toUserReg() {
        return "userReg";
    }

    @PostMapping("/create")
    public ModelAndView createUser(@RequestBody MultiValueMap<String,String> form){
        
        User user = new User();
        user.setEmail(form.getFirst("email"));
        // user.setUsername(form.getFirst("username"));
        user.setPassword(form.getFirst("password"));
        ModelAndView mav = new ModelAndView();

        Boolean result = uSvc.insertUser(user); 
        if (result) {
            mav.addObject("email", user.getEmail());
            mav.setViewName("registered");
        } else {
            mav.setViewName("error");
        }
        return mav;
    }

   

    @PostMapping(path="/directToLogin")
        public String backToLoginPage(){
            return "index";
    }


}

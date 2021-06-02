package com.mike.controller;

import com.mike.service.SecurityServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private static final Logger LOG = LogManager.getLogger(HomeController.class);

    SecurityServiceImpl securityService;

    @Autowired
    public HomeController(SecurityServiceImpl securityService){
        this.securityService = securityService;
    }

    @GetMapping(value = "/")
    public String getFirstPage() {
        ModelAndView modelAndView = new ModelAndView();
        String role = "";
        if (securityService.findLoggedInUsersRole() != null) {
            role = securityService.findLoggedInUsersRole();
            LOG.info(role);
        }
        modelAndView.setViewName("MainPage");
        switch(role) {
            case "user" :
                return "redirect:/user";
            case "admin" :
                return "redirect:/admin";
            default:
                return "login";
        }
    }
}

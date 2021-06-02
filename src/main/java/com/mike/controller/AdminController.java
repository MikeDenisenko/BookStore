package com.mike.controller;

import com.mike.dto.UserDto;
import com.mike.model.User;
import com.mike.service.SecurityServiceImpl;
import com.mike.service.UserServiceImpl;
import com.mike.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    private final UserServiceImpl userService;

    private final UserValidator userValidator;

    private final SecurityServiceImpl securityService;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, UserValidator userValidator, SecurityServiceImpl securityService){
        this.userService = userServiceImpl;
        this.userValidator = userValidator;
        this.securityService = securityService;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new UserDto());
        modelAndView.setViewName("RegistrationPage");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public String registration(@Valid @ModelAttribute("user") UserDto userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            userForm.setCaptcha("");
            return "RegistrationPage";
        }
        userService.save(userService.getUserFromDto(userForm));
        securityService.autoLogin(userForm.getName(), userForm.getPassword());
        return "redirect:/";
    }

    @GetMapping(value = "/admin")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
        modelAndView.addObject("users", users);
        modelAndView.setViewName("AdminPage");
        return modelAndView;
    }

    @DeleteMapping(value = "/admin/users/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin");
        userService.delete(id);
        return modelAndView;
    }

    @PostMapping(value = "/admin/users/{id}")
    public ModelAndView editUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.find(id);
        user.setPassword("");
        modelAndView.addObject("user", userService.toUserDto(user));
        modelAndView.setViewName("EditUser");
        return modelAndView;
    }

    @PatchMapping(value = "/admin/users/edit")
    public ModelAndView editUser(@Valid @ModelAttribute ("user") UserDto user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin");
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("EditUser");
            return modelAndView;
        }

        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
        userService.encodePasswordUpdate(userService.getUserFromDto(user));
        return modelAndView;
    }

    @GetMapping(value = "/admin/users/add")
    public ModelAndView addUserRequestForm () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new UserDto());
        modelAndView.setViewName("CreateUser");
        return modelAndView;

    }

    @PostMapping(value = "/admin/users/add")
    public ModelAndView addUser(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin");
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("CreateUser");
            return modelAndView;
        }
        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
        userService.save(userService.getUserFromDto(user));
        return modelAndView;
    }

    @GetMapping(value = "/admin/profile")
    public ModelAndView userProfile() {
        ModelAndView modelAndView = new ModelAndView("AdminProfilePage");
        modelAndView.addObject("currentUser", securityService.findLoggedInUsername());
        modelAndView.addObject("user", userService.findUserByName(securityService.findLoggedInUsername()));
        return modelAndView;
    }
}


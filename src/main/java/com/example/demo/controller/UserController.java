package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repos.UserRepo;
//import com.example.demo.service.UserService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    @Autowired
    private UserService userSevice;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepo.findByUsername(userDetails.getUsername());
        if(user!=null) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("ip", user.getIp());
            model.addAttribute("timezone", user.getTimezone());
        }
        return "profile";
    }

    @PostMapping("/edit")
    public String updateProfile(User user, @RequestParam String password ,@RequestParam Integer timezone) {
        userSevice.updateProfile(user, password);
        return "profile";
    }

}

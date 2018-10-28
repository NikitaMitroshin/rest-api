package com.example.restapi.controller;

import com.example.restapi.model.UserModel;
import com.example.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap modelMap) {
        modelMap.addAttribute("time", new Date());
        modelMap.addAttribute("message", "hello world");
        List<UserModel> allUsers = service.getAllUsers();
        modelMap.addAttribute("allUsers", allUsers);
        return "welcome";
    }
}

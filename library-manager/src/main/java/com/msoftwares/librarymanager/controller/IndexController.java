package com.msoftwares.librarymanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/home")
public class IndexController {

    @GetMapping
    public ModelAndView modelAndView(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}

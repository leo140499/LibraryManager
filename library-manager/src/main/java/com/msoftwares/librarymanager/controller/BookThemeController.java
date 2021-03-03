package com.msoftwares.librarymanager.controller;

import com.msoftwares.librarymanager.models.Entities.BookTheme;
import com.msoftwares.librarymanager.models.Entities.Library;
import com.msoftwares.librarymanager.models.Repo.BookThemeRepository;
import com.msoftwares.librarymanager.models.Repo.LibraryRepository;
import com.msoftwares.librarymanager.models.Services.BookService;
import com.msoftwares.librarymanager.models.Services.BookThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(path ="/api/bt")
public class BookThemeController {

    @Autowired
    BookThemeService bookThemeService;

    @GetMapping(path = "/all")
    public ModelAndView getThemes(){
        ModelAndView modelAndView = new ModelAndView("themeTemplate");
        modelAndView.addObject("addTheme", new BookTheme());
        modelAndView.addObject("getTheme", bookThemeService.getThemes());
        return modelAndView;
    }

    @PostMapping(path = "/createTheme")
    public RedirectView createTheme(@ModelAttribute BookTheme bookTheme){
        bookThemeService.saveTheme(bookTheme);
        return new RedirectView("http://localhost:8080/api/bt/all");
    }


}

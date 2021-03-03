package com.msoftwares.librarymanager.controller;

import com.msoftwares.librarymanager.models.Entities.Author;
import com.msoftwares.librarymanager.models.Entities.Library;
import com.msoftwares.librarymanager.models.Repo.AuthorRepository;
import com.msoftwares.librarymanager.models.Repo.LibraryRepository;
import com.msoftwares.librarymanager.models.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(path ="/api/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping(path = "/all")
    public ModelAndView getAuthors(){
        ModelAndView modelAndView = new ModelAndView("authorsTemplate");
        modelAndView.addObject("newAuthor", new Author());
        modelAndView.addObject("getAuthors", authorService.getAuthors());
        return modelAndView;
    }

    @PostMapping(path = "/create")
    public RedirectView saveAuthor(@ModelAttribute Author author){
        authorService.saveAuthor(author);
        return new RedirectView("http://localhost:8080/api/authors/all");
    }


}

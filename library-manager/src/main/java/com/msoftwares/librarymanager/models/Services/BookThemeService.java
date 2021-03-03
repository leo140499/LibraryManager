package com.msoftwares.librarymanager.models.Services;

import com.msoftwares.librarymanager.models.Entities.BookTheme;
import com.msoftwares.librarymanager.models.Repo.BookThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookThemeService {

    @Autowired
    BookThemeRepository bookThemeRepository;

    //get all themes
    public List<BookTheme> getThemes(){return bookThemeRepository.findAll();}

    //save theme
    public void saveTheme(BookTheme bookTheme){ bookThemeRepository.save(bookTheme);}

    //get by id
    public BookTheme getThemeById(int id){return bookThemeRepository.findById(id).get();}

    //delete
    public void deleteTheme(BookTheme bookTheme){bookThemeRepository.delete(bookTheme);}


}

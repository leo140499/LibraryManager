package com.msoftwares.librarymanager.controller;

import com.msoftwares.librarymanager.models.Entities.Author;
import com.msoftwares.librarymanager.models.Entities.Book;
import com.msoftwares.librarymanager.models.Entities.BookTheme;
import com.msoftwares.librarymanager.models.Entities.Library;
import com.msoftwares.librarymanager.models.Repo.AuthorRepository;
import com.msoftwares.librarymanager.models.Repo.BookRepository;
import com.msoftwares.librarymanager.models.Repo.BookThemeRepository;
import com.msoftwares.librarymanager.models.Repo.LibraryRepository;
import com.msoftwares.librarymanager.models.Services.AuthorService;
import com.msoftwares.librarymanager.models.Services.BookService;
import com.msoftwares.librarymanager.models.Services.BookThemeService;
import com.msoftwares.librarymanager.models.Services.LibraryService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.text.html.HTML;
import java.util.List;

@RestController
@RequestMapping(path ="/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    BookThemeService bookThemeService;

    //get all books
    @GetMapping(path = "/all")
    public ModelAndView getBooks(){
        ModelAndView modelAndView = new ModelAndView("booksTemplate");
        modelAndView.addObject("newBook", new Book());
        modelAndView.addObject("getAuthors", authorService.getAuthors());
        modelAndView.addObject("getBooks", bookService.getBooks());
        modelAndView.addObject("getThemes", bookThemeService.getThemes());
        return modelAndView;
    }

    //create book
    @PostMapping(path = "/create")
    public RedirectView createBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return new RedirectView("http://localhost:8080/api/books/all");
    }

    //get details
    @GetMapping(path = "/details/{id}")
    public ModelAndView getDetails(@PathVariable("id") Integer id){
        Book book = bookService.getBookById(id);
        ModelAndView modelAndView = new ModelAndView("bookDetails");
        modelAndView.addObject("bookId", book);
        List<Author> unselectedAuthors = authorService.getAuthors();
        List<BookTheme> unselectedBookThemes = bookThemeService.getThemes();
        unselectedAuthors.removeAll(book.getAuthors());
        unselectedBookThemes.removeAll(book.getBookThemes());
        modelAndView.addObject("authors", unselectedAuthors);
        modelAndView.addObject("bookThemes", unselectedBookThemes);

        return modelAndView;
    }

    //register authors
    @PostMapping(path = "/registerDetails")
    public RedirectView registerDetails(@ModelAttribute Author author, @RequestParam Integer isbn){
        Book book = bookService.getBookById(isbn);
        author = authorService.getAuthorById(author.getId());

        book.getAuthors().add(author);

        bookService.saveBook(book);
        return new RedirectView("http://localhost:8080/api/books/details/" + isbn);
    }

    //register themes
    @PostMapping(path = "/registerTheme")
    public RedirectView registerThemes(@ModelAttribute BookTheme bookTheme, @RequestParam Integer isbn){
        Book book = bookService.getBookById(isbn);
        bookTheme = bookThemeService.getThemeById(bookTheme.getId());

        book.getBookThemes().add(bookTheme);
        bookService.saveBook(book);
        return new RedirectView("http://localhost:8080/api/books/details/" + isbn);
    }



}

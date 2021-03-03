package com.msoftwares.librarymanager.controller;

import com.msoftwares.librarymanager.models.Entities.Book;
import com.msoftwares.librarymanager.models.Entities.Client;
import com.msoftwares.librarymanager.models.Entities.Library;
import com.msoftwares.librarymanager.models.Repo.LibraryRepository;
import com.msoftwares.librarymanager.models.Services.BookService;
import com.msoftwares.librarymanager.models.Services.ClientService;
import com.msoftwares.librarymanager.models.Services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path ="/api/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @Autowired
    BookService bookService;

    @Autowired
    ClientService clientService;


    @GetMapping(path = "/all")
    public ModelAndView getLibraries(){
        ModelAndView modelAndView = new ModelAndView("libraryTemplate");
        modelAndView.addObject("newLibrary", new Library());
        modelAndView.addObject("getLibraries", libraryService.getLibrary());
        return modelAndView;
    }

    @GetMapping(path = "/details/{id}")
    public ModelAndView getDetails(@PathVariable("id") Integer id){
        Library library = libraryService.findLibById(id);
        ModelAndView modelAndView = new ModelAndView("libraryEdit");
        modelAndView.addObject("library", library );
        List<Book> unselectedBooks = bookService.getBooks();
        unselectedBooks.removeAll(library.getBook());
        modelAndView.addObject("availableBooks", unselectedBooks);
        List<Client> unselectedClients = clientService.getClients();
        unselectedClients.removeAll(library.getClients());
        modelAndView.addObject("availableClients", unselectedClients);
        return modelAndView;
    }


    @PostMapping(path = "/editLib")
    public RedirectView editLib(@ModelAttribute Book book, @RequestParam Integer libId){
        Library library = libraryService.findLibById(libId);
        book = bookService.getBookById(book.getIsbn());
        book.setLibrary(library);
        library.getBook().add(book);
        libraryService.saveLibrary(library);


        return new RedirectView("http://localhost:8080/api/library/details/" + libId);
    }

    @PostMapping(path = "/create")
    public RedirectView createLibrary(@ModelAttribute Library library){
        libraryService.saveLibrary(library);
        return new RedirectView("http://localhost:8080/api/library/all");
    }

    @PostMapping(path = "/registerClient")
    public RedirectView registerClient(@ModelAttribute Client client, @RequestParam Integer libId){
        Library library = libraryService.findLibById(libId);
        client = clientService.findById(client.getId());
        library.getClients().add(client);
        libraryService.saveLibrary(library);
        return new RedirectView("http://localhost:8080/api/library/details/" + libId);
    }


}

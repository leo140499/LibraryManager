package com.msoftwares.librarymanager.controller;

import com.msoftwares.librarymanager.models.Entities.Book;
import com.msoftwares.librarymanager.models.Entities.Client;
import com.msoftwares.librarymanager.models.Entities.Library;
import com.msoftwares.librarymanager.models.Repo.ClientRepository;
import com.msoftwares.librarymanager.models.Repo.LibraryRepository;
import com.msoftwares.librarymanager.models.Services.BookService;
import com.msoftwares.librarymanager.models.Services.ClientService;
import com.msoftwares.librarymanager.models.Services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping(path ="/api/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    BookService bookService;

    @Autowired
    LibraryService libraryService;

    @GetMapping(path = "/all")
    public ModelAndView getClients(){
        ModelAndView modelAndView = new ModelAndView("clientTemplate");
        modelAndView.addObject("newClient", new Client());
        modelAndView.addObject("getClients", clientService.getClients());
        return modelAndView;
    }

    @GetMapping( path = "/details/{id}")
    public ModelAndView clientDetails(@PathVariable("id") Integer id){
        Client client = clientService.findById(id);
        ModelAndView modelAndView = new ModelAndView("clientDetails");
        modelAndView.addObject("clientId", client);
        List<Book> unselectedBooks = bookService.getBooks();
        unselectedBooks.removeAll(client.getBook());
        modelAndView.addObject("books", unselectedBooks);
        List<Book> clientBooks = client.getBook();
        modelAndView.addObject("cb", clientBooks);
        List<Library> clientLibraries = client.getLibraries();
        modelAndView.addObject("clientLibraries", clientLibraries);
        return modelAndView;
    }

    @PostMapping(path = "/create")
    public RedirectView createClient(Client client){
        clientService.saveClient(client);
        return new RedirectView("http://localhost:8080/api/clients/all");
    }

    @PostMapping(path = "/registerDetails")
    public RedirectView clientDetails(@ModelAttribute Book book, @RequestParam Integer id){
         Client client = clientService.findById(id);
         book = bookService.getBookById(book.getIsbn());
         book.setClient(client);
         client.getBook().add(book);
         clientService.saveClient(client);
        return new RedirectView("http://localhost:8080/api/clients/details/" + id);
    }

    @PostMapping(path = "/delete")
    public RedirectView deleteClient(@RequestParam Integer id){
        Client client = clientService.findById(id);
        clientService.deleteClient(client);
        return new RedirectView("http://localhost:8080/api/clients/all");
    }

    @PostMapping(path = "/deleteBooks")
    public RedirectView deleteBooks(@ModelAttribute Book book, @RequestParam Integer id){
        Client client = clientService.findById(id);
        book = bookService.getBookById(book.getIsbn());
        client.getBook().remove(book);
        book.setClient(null);
        clientService.saveClient(client);
        return new RedirectView("http://localhost:8080/api/clients/details/" + id);
    }

}

package com.msoftwares.librarymanager.models.Services;

import com.msoftwares.librarymanager.models.Entities.Book;
import com.msoftwares.librarymanager.models.Entities.Library;
import com.msoftwares.librarymanager.models.Repo.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    BookService bookService;


    //get all
    public List<Library> getLibrary(){return libraryRepository.findAll();}

    //get by id
    public Library findLibById(int id){return libraryRepository.findById(id).get();}

    //save
    public void saveLibrary(Library library){libraryRepository.save(library);}

    //delete
    public void deleteLibrary(Library library){libraryRepository.delete(library);}

    public void addBook(int isbn, int libId){
        Library library = findLibById(libId);
        Book book = bookService.getBookById(isbn);
        saveLibrary(library);
    }

}

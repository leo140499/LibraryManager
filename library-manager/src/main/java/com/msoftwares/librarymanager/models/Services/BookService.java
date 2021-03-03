package com.msoftwares.librarymanager.models.Services;

import com.msoftwares.librarymanager.models.Entities.Author;
import com.msoftwares.librarymanager.models.Entities.Book;
import com.msoftwares.librarymanager.models.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    //get all books
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    //save book
    public void saveBook(Book book){
        bookRepository.save(book);
    }

    //find books by id
    public Book getBookById(int id){
        return bookRepository.findById(id).get();
    }

    //delete
    public void deleteBook(Book book){
        bookRepository.delete(book);
    }



}

package com.msoftwares.librarymanager;

import com.msoftwares.librarymanager.models.Entities.Book;
import com.msoftwares.librarymanager.models.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagerApplication {


    public static void main(String[] args) {
        SpringApplication.run(LibraryManagerApplication.class, args);

    }

}

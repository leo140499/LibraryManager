package com.msoftwares.librarymanager.models.Services;

import com.msoftwares.librarymanager.models.Entities.Author;
import com.msoftwares.librarymanager.models.Repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public void saveAuthor(Author author){
        authorRepository.save(author);
    }

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id){
        return authorRepository.findById(id).get();
    }

    public void deleteAuthor(Author author){authorRepository.delete(author);}

}

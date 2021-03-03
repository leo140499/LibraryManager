package com.msoftwares.librarymanager.models.Repo;

import com.msoftwares.librarymanager.models.Entities.Book;
import com.msoftwares.librarymanager.models.Entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

}

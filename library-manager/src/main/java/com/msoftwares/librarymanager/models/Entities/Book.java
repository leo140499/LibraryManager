package com.msoftwares.librarymanager.models.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    public Book(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int isbn;

    @Column
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<BookTheme> bookThemes = new ArrayList<>();

    @ManyToOne
    private Library library;

    @ManyToOne
    private Client client;


}

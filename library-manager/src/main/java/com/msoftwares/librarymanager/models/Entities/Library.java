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
public class Library {

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Client> clients = new ArrayList<>();

    @OneToMany
    private List<Book> book = new ArrayList<>();


}

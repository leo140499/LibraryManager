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
public class Client {

    public Client(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String gender;

    @ManyToMany(mappedBy = "clients", cascade = CascadeType.ALL)
    private List<Library> libraries = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Book> book = new ArrayList<>();


}

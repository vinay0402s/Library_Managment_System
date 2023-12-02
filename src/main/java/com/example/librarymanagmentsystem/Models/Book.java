package com.example.librarymanagmentsystem.Models;

import com.example.librarymanagmentsystem.CustomException.Enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(unique = true)
    private String title;

    public Book(String title, Boolean isAvailable, Genre genre, Date publicationDate, Integer price) {
        this.title = title;
        this.isAvailable = isAvailable;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.price = price;
    }

    private Boolean isAvailable;

    @Enumerated(value=EnumType.STRING)
    private Genre genre;

    private Date publicationDate;

    private Integer price;

    @ManyToOne//foreign key
    @JoinColumn
    @JsonIgnore
    private Author author;
    //unidirectional mapping

    @OneToMany(mappedBy = "book" ,cascade = CascadeType.ALL)
    private List<Transcation> transcationList = new ArrayList<>();



}

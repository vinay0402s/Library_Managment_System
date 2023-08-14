package com.example.librarymanagmentsystem.Models;

import com.example.librarymanagmentsystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String title;

    private Boolean isAvailable;

    @Enumerated(value=EnumType.STRING)
    private Genre genre;

    private Date publicationDate;

    private Integer price;

    @ManyToOne//foreign key
    @JoinColumn
    private Author author;
    //unidirectional mapping

}

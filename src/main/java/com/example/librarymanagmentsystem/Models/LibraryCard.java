package com.example.librarymanagmentsystem.Models;


import com.example.librarymanagmentsystem.Enums.CardStatus;
import jakarta.persistence.*;

@Entity
@Table
public class LibraryCard {

    @Id//used for the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //it will automatic generate roll no
    private int cardNo;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private int noOfBooksIssued;

    //foreign key to be added in the child table
    @OneToOne
    @JoinColumn
   // @PrimaryKeyJoinColumn(name="roll_No")
    private Student student;

}

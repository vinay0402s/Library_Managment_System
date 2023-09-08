package com.example.librarymanagmentsystem.Models;


import com.example.librarymanagmentsystem.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class LibraryCard {

    @Id//used for the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //it will automatic generate roll no
    private int cardNo;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private Integer noOfBooksIssued;

    //foreign key to be added in the child table
    @OneToOne
    @JoinColumn
   // @PrimaryKeyJoinColumn(name="roll_No")
    private Student student;

    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    private List<Transcation> transcationList =new ArrayList<>();

}

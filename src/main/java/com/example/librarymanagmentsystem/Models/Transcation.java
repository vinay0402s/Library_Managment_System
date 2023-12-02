package com.example.librarymanagmentsystem.Models;

import com.example.librarymanagmentsystem.CustomException.Enums.TransactionStatus;
import com.example.librarymanagmentsystem.CustomException.Enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="transaction")
public class Transcation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transcationId ;

    @CreationTimestamp
    private Date createdAt; //automatically due to annotation @creationtimestamp
    //automatically time will be recorded when new row is added to sb


    @UpdateTimestamp
    private Date updateAt;

    //since this are enum so i want to stoer it in db so for that we have write that
    @Enumerated(value=EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(value=EnumType.STRING)
    private TransactionType transactionType; //they are enum = contain only fixed values;

    private Integer fineAmount ;

    public Transcation(TransactionStatus transactionStatus, TransactionType transactionType, Integer fineAmount) {
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.fineAmount = fineAmount;
    }

    public Transcation(){

    }

    @ManyToOne //since it is child
    @JoinColumn
    @JsonIgnore
    private Book book;  //this is how connecting to book

    @ManyToOne
    @JoinColumn
    @JsonIgnore //to aoid infinite loop=recursion
    private LibraryCard libraryCard;//connecting to librarycard

}

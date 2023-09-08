package com.example.librarymanagmentsystem.Repositories;

import com.example.librarymanagmentsystem.Enums.TransactionStatus;
import com.example.librarymanagmentsystem.Enums.TransactionType;
import com.example.librarymanagmentsystem.Models.Book;
import com.example.librarymanagmentsystem.Models.LibraryCard;
import com.example.librarymanagmentsystem.Models.Transcation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transcation,Integer> {


    List<Transcation> findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType(Book book, LibraryCard card, TransactionStatus transactionStatus, TransactionType transactionType);

    List<Transcation> findTransactionByTransactionStatusAndTransactionType(TransactionStatus transactionStatus, TransactionType transactionType);

}

package com.example.librarymanagmentsystem.Services;


import com.example.librarymanagmentsystem.CustomException.BookNotAvailableException;
import com.example.librarymanagmentsystem.CustomException.BookNotFoundException;
import com.example.librarymanagmentsystem.CustomException.LibraryCardISInvalidException;
import com.example.librarymanagmentsystem.CustomException.LibraryCardStatusException;
import com.example.librarymanagmentsystem.Enums.CardStatus;
import com.example.librarymanagmentsystem.Enums.TransactionStatus;
import com.example.librarymanagmentsystem.Enums.TransactionType;
import com.example.librarymanagmentsystem.Models.Book;
import com.example.librarymanagmentsystem.Models.LibraryCard;
import com.example.librarymanagmentsystem.Models.Transcation;
import com.example.librarymanagmentsystem.Repositories.BookRepository;
import com.example.librarymanagmentsystem.Repositories.CardRepository;
import com.example.librarymanagmentsystem.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${book.maxLimit}")
    private Integer maxBookLimit;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    public String issueBook(Integer bookId, Integer cardId)throws Exception{

        //book related exception

        Transcation transaction = new Transcation(TransactionStatus.PENDING, TransactionType.ISSUED,0);

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(!optionalBook.isPresent()){
            throw new BookNotFoundException("Book Id is incorrect");
        }

        Book book = optionalBook.get();
        if(book.getIsAvailable()==Boolean.FALSE){
            throw new BookNotAvailableException("Book is not Available");
        }

        //card related exception
        Optional<LibraryCard> libraryCardOptional = cardRepository.findById(cardId);

        if(!libraryCardOptional.isPresent()){
          throw new LibraryCardISInvalidException("Card Id Entered is Invalid");
        }

        LibraryCard libraryCard = libraryCardOptional.get();
        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction = transactionRepository.save(transaction);
            //here only saved transaction not book and card why???
            //ans == 18 aug lect

            throw new LibraryCardStatusException("Card is not in right Status");
        }

        if(libraryCard.getNoOfBooksIssued()>=maxBookLimit){

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction = transactionRepository.save(transaction);

            throw new Exception("Already max limit books has issued");
        }
        /*all transaction cases and invalid scenarios are over*/
        //we have reached at the success point

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //upate the card and book entity
        book.setIsAvailable(Boolean.FALSE);
        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()+1);

        ///we need to do unidirectional mapping
        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        //we are saving it to preven duplicate entry
        Transcation newTransactionwithId = transactionRepository.save(transaction);

        //we need to do in parent class
        book.getTranscationList().add(newTransactionwithId);
        libraryCard.getTranscationList().add(newTransactionwithId);

        //what all neeed stobe saved
        bookRepository.save(book);
        cardRepository.save(libraryCard);

        return "Transaction has been saved succesfully";

    }

    public String returnBook(Integer bookId, Integer cardId){
        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();

        List<Transcation> transcationList = transactionRepository.findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType(book,card,TransactionStatus.SUCCESS, TransactionType.ISSUED);

        Transcation latestTransaction = transcationList.get(transcationList.size()-1);
        //get the latest transaction

        Date issueDate = latestTransaction.getCreatedAt();
        Integer year = issueDate.getYear()+1900;

        //get curretn time in milisecond
        long milliSecondTime = Math.abs(System.currentTimeMillis()-issueDate.getTime());
        long no_of_days_issued = TimeUnit.DAYS.convert(milliSecondTime,TimeUnit.MILLISECONDS);

        int fineAmount =0;
        if(no_of_days_issued>15){
            fineAmount = (int) ((no_of_days_issued-15)*5);
        }
        //fine calculated

        book.setIsAvailable(Boolean.TRUE);  //now book==avialble
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1); //

        Transcation transcation = new Transcation(TransactionStatus.SUCCESS, TransactionType.RETURN,fineAmount);

        transcation.setBook(book);
        transcation.setLibraryCard(card);

        Transcation newTransactionWithId  = transactionRepository.save(transcation);

        book.getTranscationList().add(newTransactionWithId);
        card.getTranscationList().add(newTransactionWithId);

        //saving the parents
        bookRepository.save(book);
        cardRepository.save(card);

        return "Book has succesfully been returned";
    }

    public Integer getTotalFine2023(){
        Integer totalFine =0;
        List<Transcation> transcationList = transactionRepository.findTransactionByTransactionStatusAndTransactionType(TransactionStatus.SUCCESS,TransactionType.RETURN);

        if(transcationList.size()==0){
            System.out.println("No  transaction happens in 2023");
            return 0;
        }

        for(Transcation transcation : transcationList){
            Date issueDate = transcation.getCreatedAt();
            Integer year = issueDate.getYear()+1900;
            if(year==2023){
                totalFine += transcation.getFineAmount();
            }
        }

        return totalFine;
    }



}

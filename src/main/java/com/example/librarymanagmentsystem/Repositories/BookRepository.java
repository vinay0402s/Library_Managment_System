package com.example.librarymanagmentsystem.Repositories;

import com.example.librarymanagmentsystem.CustomException.Enums.Genre;
import com.example.librarymanagmentsystem.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findBooksByGenre(Genre genre);

    //select  * from book where genre == userEnteredGenre
}

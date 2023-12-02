package com.example.librarymanagmentsystem.Services;

import com.example.librarymanagmentsystem.CustomException.Enums.Genre;
import com.example.librarymanagmentsystem.Models.Author;
import com.example.librarymanagmentsystem.Models.Book;
import com.example.librarymanagmentsystem.Repositories.AuthorRepository;
import com.example.librarymanagmentsystem.Repositories.BookRepository;
import com.example.librarymanagmentsystem.RequestDto.AddBookRequestDto;
import com.example.librarymanagmentsystem.ResponseDto.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public String addBook(AddBookRequestDto request)throws Exception{

        //validation
        //authorId should be valid

        Optional<Author> optionalAuthor = authorRepository.findById(request.getAuthorId());

        if(!optionalAuthor.isPresent()){
            throw new Exception("Author Id Entered is Incorrect");
        }

        Book book = new Book(request.getTitle(), request.getIsAvailable(), request.getGenre(), request.getPublicationDate(),request.getPrice());

        Author author = optionalAuthor.get();//get the author entitty from authorId
        //Entities will go inside the database and entitites will only come out from DB
        //GOT THW BOOK OBJECT
        //set the FK Variable
        //Since its bidirectional : need to set in both child and parent class

        //set the parent entity in child class
        book.setAuthor(author);

        //Setting in the parent
        //author.getBookList().add(book);  OR
        List<Book> list = author.getBookList();
        list.add(book);
        author.setBookList(list);

        //we need to save them
        //save only the parent : child will automatcally get saved

        authorRepository.save(author);

        return "Book has been succesfully added and updated";
    }

    public List<BookResponseDto> getBookListByGenre(Genre genre){

        List<Book> bookList = bookRepository.findBooksByGenre(genre);

        List<BookResponseDto> responseList = new ArrayList<>();

        for(Book book : bookList){
            BookResponseDto bookResponseDto = new BookResponseDto(book.getTitle(),book.getIsAvailable(),
                                                                  book.getGenre(), book.getPublicationDate(),
                                                                 book.getPrice(),book.getAuthor().getName());

            responseList.add(bookResponseDto);
        }
        return responseList;
    }


}

package com.example.librarymanagmentsystem.Controller;


import com.example.librarymanagmentsystem.Enums.Genre;
import com.example.librarymanagmentsystem.Models.LibraryCard;
import com.example.librarymanagmentsystem.RequestDto.AddBookRequestDto;
import com.example.librarymanagmentsystem.ResponseDto.BookResponseDto;
import com.example.librarymanagmentsystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequestDto addBookRequestDto){

        try{
            String result = bookService.addBook(addBookRequestDto);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByGenre")
    public ResponseEntity getBookListByGenre(@RequestParam("genre")Genre genre){

        List<BookResponseDto> ResponseDtoList = bookService.getBookListByGenre(genre);
        return new ResponseEntity<>(ResponseDtoList,HttpStatus.OK);
    }
}


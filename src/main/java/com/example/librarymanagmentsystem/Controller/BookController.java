package com.example.librarymanagmentsystem.Controller;


import com.example.librarymanagmentsystem.CustomException.Enums.Genre;
import com.example.librarymanagmentsystem.RequestDto.AddBookRequestDto;
import com.example.librarymanagmentsystem.ResponseDto.BookResponseDto;
import com.example.librarymanagmentsystem.Services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "BookController" , description = "To perform operation on Book Details")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(
            summary = "POST operation on BOOK",
            description = "It is used to add BOOK details in database"
    )
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequestDto addBookRequestDto){

        try{
            String result = bookService.addBook(addBookRequestDto);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "GET operation on Author",
            description = "It is used to retrive book details by genre from database"
    )
    @GetMapping("/getByGenre")
    public ResponseEntity getBookListByGenre(@RequestParam("genre")Genre genre){

        List<BookResponseDto> ResponseDtoList = bookService.getBookListByGenre(genre);
        return new ResponseEntity<>(ResponseDtoList,HttpStatus.OK);
    }
}


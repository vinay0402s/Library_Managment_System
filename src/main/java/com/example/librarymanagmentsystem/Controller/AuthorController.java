package com.example.librarymanagmentsystem.Controller;

import com.example.librarymanagmentsystem.RequestDto.UpdateNameAndPenNameRequest;
import com.example.librarymanagmentsystem.Models.Author;
import com.example.librarymanagmentsystem.Services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@Slf4j
@Tag(name = "AuthorController" , description = "To perform operation on Author Details")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Operation(
            summary = "POST operation on Author",
            description = "It is used to add Author details in database"
    )
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author){

        //@RequestBody Author author

        //1 endpoint has become long
        //Exposed in the URL itself
        try{
           String result = authorService.addAuthor(author);
           return new ResponseEntity("author data is added to database",HttpStatus.OK);
        }catch (Exception e){
            log.error("Author could not be added to db {}",e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(
            summary = "PUT operation on Author",
            description = "It is used to update AuthorName and PenName details in database"
    )
    @PutMapping("/updateNameAndPenName")
    public String updateAuthorNameAndPenName(@RequestBody UpdateNameAndPenNameRequest updateNameAndPenNameRequest){

        try{
           String result = authorService.UpdateNameAndPenName(updateNameAndPenNameRequest);
           return result;
        }catch(Exception e){
            return "Author Id is Inavalid"+e.getMessage();
        }
    }

    @Operation(
            summary = "GET operation on Author",
            description = "It is used to retrive Author details from database"
    )
    @GetMapping("/getAuthor")
    public Author getAuthor(@RequestParam ("authorId")Integer authorId){
       return authorService.getAuthorById(authorId);
    }


}

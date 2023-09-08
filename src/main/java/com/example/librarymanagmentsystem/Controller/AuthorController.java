package com.example.librarymanagmentsystem.Controller;

import com.example.librarymanagmentsystem.RequestDto.UpdateNameAndPenNameRequest;
import com.example.librarymanagmentsystem.Models.Author;
import com.example.librarymanagmentsystem.Services.AuthorService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@Slf4j

public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author){

        //@RequestBody Author author

        //1 endpoint has become long
        //Exposed in the URL itself
        try{
           String result = authorService.addAuthor(author);
           return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e){
            log.error("Author could not be added to db {}",e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateNameAndPenName")
    public String updateAuthorNameAndPenName(@RequestBody UpdateNameAndPenNameRequest updateNameAndPenNameRequest){

        try{
           String result = authorService.UpdateNameAndPenName(updateNameAndPenNameRequest);
           return result;
        }catch(Exception e){
            return "Author Id is Inavalid"+e.getMessage();
        }
    }

    @GetMapping("/getAuthor")
    public Author getAuthor(@RequestParam ("authorId")Integer authorId){
       return authorService.getAuthorById(authorId);
    }


}

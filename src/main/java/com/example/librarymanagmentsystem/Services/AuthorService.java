package com.example.librarymanagmentsystem.Services;

import com.example.librarymanagmentsystem.Models.Author;
import com.example.librarymanagmentsystem.Repositories.AuthorRepository;
import com.example.librarymanagmentsystem.RequestDto.UpdateNameAndPenNameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author)throws Exception{

        //validation checks
        if(author.getAuthorId()!=null){
            throw new Exception("Author Id should not sent as parameter");
        }

        authorRepository.save(author);
        return "author has been added successfully to the db";
    }

    public String UpdateNameAndPenName(UpdateNameAndPenNameRequest request)throws Exception{
        //validation
        //get-update-save
        //we can save or get the entities
        Optional<Author> authorOptional = authorRepository.findById(request.getAuthorId());

        if(!authorOptional.isPresent()){
            throw new Exception("Author Id is Invalid");
        }
        Author author = authorOptional.get();

        author.setName(request.getNewName());
        author.setPenName(request.getNewPenName());

        authorRepository.save(author);

        return  "Author NAme and PenName has been updated";
    }

    public Author getAuthorById(Integer authorId){

        /*Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        Author author = optionalAuthor.get();*/

        Author author = authorRepository.findById(authorId).get(); //shortcut==to get author
        return author;
    }
}

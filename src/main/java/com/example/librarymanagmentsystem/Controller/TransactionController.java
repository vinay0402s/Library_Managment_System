package com.example.librarymanagmentsystem.Controller;

import com.example.librarymanagmentsystem.Services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@Tag(name = "TransactionController" , description = "To perform operation on Transaction Details")

public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Operation(
            summary = "POST operation on TRANSACTION",
            description = "It is used to issue book using bookId and cardId in database"
    )
    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestParam("bookId")Integer bookId, @RequestParam("cardId")Integer cardId){
        try{
            String result = transactionService.issueBook(bookId,cardId);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(
            summary = "GET operation on TRANSACTION",
            description = "It is used to retrive total fine in 2023 details in database"
    )
    @GetMapping("/getTotalFine2023")
    public ResponseEntity getTotalFine2023(){

        try{
            return new ResponseEntity(transactionService.getTotalFine2023(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}

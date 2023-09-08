package com.example.librarymanagmentsystem.Controller;

import com.example.librarymanagmentsystem.Services.TransactionService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

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

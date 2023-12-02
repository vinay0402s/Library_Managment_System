package com.example.librarymanagmentsystem.Controller;


import com.example.librarymanagmentsystem.Models.LibraryCard;
import com.example.librarymanagmentsystem.Models.Student;
import com.example.librarymanagmentsystem.Services.LibraryCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@Slf4j
@Tag(name = "CardController" , description = "To perform operation on Library Card Details")
public class CardController {

    @Autowired
    private LibraryCardService cardService;


    @Operation(
            summary = "POST operation on LIBRARY CARD",
            description = "It is used to create Library card details in database"
    )
    @PostMapping("/create")
    public String addCard(@RequestBody LibraryCard card)
    {
        return cardService.addCard(card);
    }

    @Operation(
            summary = "PUT operation on LIBRARY CARD",
            description = "It is used to update details in database"
    )
    @PutMapping("/issueToStudent")
    public ResponseEntity issueToStudent(@RequestParam("cardId") Integer cardId, @RequestParam("rollNo") Integer rollNo){
        try{
            String result = cardService.associateToStudent(cardId, rollNo);
            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e){
            log.error("Error in associating card to student", e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(
            summary = "GET operation on LIBRARY CARD",
            description = "It is used to get the list of student whose card status is not active in database"
    )
    //get the list of student whose card status is not active
    @GetMapping("/getStudentListCardStatusIsACTIVE")
    public ResponseEntity<List<Student>> getStudentListCardStatusIsACTIVE(){

        try{
            List<Student> studentList = cardService.getInactiveStudents();
            return new ResponseEntity<>(studentList,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

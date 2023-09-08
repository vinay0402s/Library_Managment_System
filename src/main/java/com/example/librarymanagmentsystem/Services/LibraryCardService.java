package com.example.librarymanagmentsystem.Services;

import com.example.librarymanagmentsystem.Enums.CardStatus;
import com.example.librarymanagmentsystem.Models.LibraryCard;
import com.example.librarymanagmentsystem.Models.Student;
import com.example.librarymanagmentsystem.Repositories.CardRepository;
import com.example.librarymanagmentsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryCardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String addCard(LibraryCard libraryCard){
        cardRepository.save(libraryCard);
        return "card has been succesfully added to the db ";
    }


    public String associateToStudent(Integer cardNo, Integer rollNo)throws Exception{

        //student should exist
        if(!studentRepository.existsById(rollNo)){
            throw new Exception("Student Id is Invalid");
        }

        ///card should also exist
        if(!cardRepository.existsById(cardNo)){
            throw new Exception("Card No is Invalid");
        }

        //it is part of bidirectional mapping
        //i need to update FOREIGN KEY VARIABLE

        Optional<Student> optional = studentRepository.findById(rollNo);
        //get the student
        Student studentObj = optional.get();

        Optional<LibraryCard> optionalLibraryCard= cardRepository.findById(cardNo);
        //get card
        LibraryCard libraryCard = optionalLibraryCard.get() ;

        //we need to set foreign key variables
        //set the student object in card object
        libraryCard.setStudent(studentObj);

        //since its a bidirectional mapping
        //int he student object we need to set the librarycard object
        studentObj.setLibraryCard(libraryCard);

        //any objec that has been updated should be saved
        //save both of them:since both were updated

        studentRepository.save(studentObj);

        //cardrepository  savings can be skipped bcz
        //student will automatically trigger for the cardrepository save funct
        //means agar parent ko save kiya to child save ho jayega
       // cardRepository.save(libraryCard);

        return "student and card saved succesfully";
    }

    public List<Student> getInactiveStudents(){

        List<Student> newList = new ArrayList<>();
        List<Student> allStudents = studentRepository.findAll();
        //add student whose card status is not active

        for(Student student : allStudents){
            if(student.getLibraryCard().getCardStatus()!= CardStatus.ACTIVE){
                newList.add(student);
            }
        }
        return newList;
    }
}

package com.example.librarymanagmentsystem.Controller;

import com.example.librarymanagmentsystem.CustomException.Enums.Department;
import com.example.librarymanagmentsystem.Models.Student;
import com.example.librarymanagmentsystem.Services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
@Tag(name = "StudentController" , description = "To perform operation on Student Details")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Operation(
            summary = "POST operation on STUDENT",
            description = "It is used to add student details in database"
    )
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student){

        try{
            String result = studentService.addStudent(student);
            return new ResponseEntity(result, HttpStatus.CREATED);
        }catch (Exception e){
            log.error("student not added successfully {}",e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(
            summary = "GET operation on LIBRARY CARD",
            description = "It is used to retrive student details by ID from database"
    )
    @GetMapping("/findDeptById")
    public ResponseEntity findDeptById(@RequestParam("Id") Integer Id){
        try {
            Department department = studentService.getDepartmentById(Id);
            return new ResponseEntity(department,HttpStatus.OK);
        }catch (Exception e){
            log.error("Department not found/Invalid Request{}",e.getMessage());
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        }
    }
}

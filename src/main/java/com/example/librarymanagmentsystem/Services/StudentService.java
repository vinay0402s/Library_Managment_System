package com.example.librarymanagmentsystem.Services;

import com.example.librarymanagmentsystem.Enums.Department;
import com.example.librarymanagmentsystem.Models.Student;
import com.example.librarymanagmentsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student) throws Exception{

        //if(student.getRollNo()!=null) or bwlow line doing same work
        if(student.getRollNo()!=null){
            throw new Exception("Id should not be sent as a parameter");
        }

        studentRepository.save(student);
        //student entity will get save to the db

        return "student has been added succesfully";
    }

    public Department getDepartmentById(Integer rollNo)throws Exception{

        Optional<Student> optionalStudent = studentRepository.findById(rollNo);

        if(!optionalStudent.isPresent()){
            throw new Exception("Roll No Entered is invalid");
        }

        Student student = optionalStudent.get();

        return student.getDepartment();
    }

    //API 3
    //Find out the student name who has read most no. of distinct books 👍
    //Incase of multiple people have read max books, return any name

    public Student getStudentReadMostDistinctBooks(){

        return null;
    }

}

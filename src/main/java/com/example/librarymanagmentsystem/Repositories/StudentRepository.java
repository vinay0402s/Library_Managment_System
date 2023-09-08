package com.example.librarymanagmentsystem.Repositories;

import com.example.librarymanagmentsystem.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}

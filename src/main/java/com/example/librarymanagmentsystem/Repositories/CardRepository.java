package com.example.librarymanagmentsystem.Repositories;

import com.example.librarymanagmentsystem.Models.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
}

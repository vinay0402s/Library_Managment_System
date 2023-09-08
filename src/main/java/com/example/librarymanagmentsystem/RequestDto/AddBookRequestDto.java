package com.example.librarymanagmentsystem.RequestDto;

import com.example.librarymanagmentsystem.Enums.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter

//it is only used for transfer of Information
public class AddBookRequestDto {


    private String title;
    private Boolean isAvailable;
    private Genre genre;
    private Date publicationDate;
    private Integer price;
    private Integer authorId;

}

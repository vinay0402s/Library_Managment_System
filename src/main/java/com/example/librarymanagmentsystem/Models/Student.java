package com.example.librarymanagmentsystem.Models;

import com.example.librarymanagmentsystem.Enums.CardStatus;
import com.example.librarymanagmentsystem.Enums.Department;
import com.example.librarymanagmentsystem.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import javax.naming.CannotProceedException;
import javax.naming.ldap.PagedResultsControl;

@Entity
@Table(name="student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id//used for the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //it will automatic generate roll no
    private Integer rollNo;

    private String name;

    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;//this gender variable is of user defined datatype : this contins only two values = MALE, FEMALE

    @Enumerated(value = EnumType.STRING)
    private Department department;

    @Column(unique = true)
    private String emailId;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private LibraryCard libraryCard;

}

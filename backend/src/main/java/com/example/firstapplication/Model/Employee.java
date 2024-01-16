package com.example.firstapplication.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private  long id ;
    private String name ;
    @Email
    private String email;
    private String jobTitle ;
    private String phone ;
    private String imageUrl;
    @Column(nullable = false,updatable = false)

    private String employeeCode ;

}

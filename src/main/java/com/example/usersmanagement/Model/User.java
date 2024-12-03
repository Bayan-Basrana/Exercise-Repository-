package com.example.usersmanagement.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty(message = "name is empty")
    @Size(min=5,message = "name must be more than 4")
    @Column(columnDefinition = "varchar(10) not null")
    private String name ;
    @NotEmpty(message = "username is empty")
    @Size(min=5,message = "username must be more than 4")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username ;
    @NotEmpty(message = "password is empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String password ;
    @NotEmpty(message = "email is empty")
    @Email(message = "must be valid email ")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String email ;
    @NotEmpty(message = "role is empty")
@Pattern(regexp = "user|admin")
    @Column(columnDefinition = "varchar(10) not null ")
    private String role ;
    @Positive(message = "age must be Positive")
    @Column(columnDefinition = "int not null")
    private Integer age;

}

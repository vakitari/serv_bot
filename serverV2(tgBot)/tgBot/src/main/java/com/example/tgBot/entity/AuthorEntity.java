package com.example.tgBot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;



@Data
public class AuthorEntity {

    private Long id;
    private String name;
    private String lastName;
    private String surName;
    private List<BookEntity> book;


    @Override
    public String toString() {
        return  name + "\n" +
                "Отчетсво - " + lastName + "\n" +
                "Фамилия - " + surName;

    }
}

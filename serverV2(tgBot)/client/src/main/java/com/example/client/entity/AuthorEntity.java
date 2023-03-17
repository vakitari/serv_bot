package com.example.client.entity;

import lombok.Data;
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
        return
                 name + ' ' + lastName + ' ' + ' '
                + surName + ' ';
    }
}

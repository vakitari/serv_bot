package com.example.client.entity;


import lombok.Data;




@Data


public class BookEntity {

    private Long id;
    private String title;
    private AuthorEntity author;
    private PublisherEntity publishing;
    private int year;
    private String king;

    @Override
    public String toString() {
        return  title + ' ' +
                " " + author +
                " " + publishing +
                " " + year +
                " " + king ;
    }
}

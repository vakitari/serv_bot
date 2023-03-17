package com.example.client.entity;


import lombok.Data;

import java.util.List;



@Data

public class PublisherEntity {
    private Long id;
    private String publisher;
    private String city;
    private List<BookEntity> book;

    @Override
    public String toString() {
        return
                 publisher + ' ' +
                 city ;
    }
}

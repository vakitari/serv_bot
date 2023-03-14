package com.example.tgBot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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
        return
                "\n" + "Название - " + title + "\n" +
                "Имя втора - " + author + "\n" +
                "Издательство - " + publishing + "\n" +
                "Год издания - " + year + "\n" +
                "Жанр - " + king + "\n";
    }
}

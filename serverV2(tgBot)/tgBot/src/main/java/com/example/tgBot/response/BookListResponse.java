package com.example.tgBot.response;


import com.example.tgBot.entity.BookEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BookListResponse extends BaseResponse{
    public BookListResponse(Iterable<BookEntity> data){
        super(true,"Книги");
        this.data = data;
    }
    private Iterable<BookEntity> data;

}

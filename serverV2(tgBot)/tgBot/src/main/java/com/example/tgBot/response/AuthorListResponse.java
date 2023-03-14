package com.example.tgBot.response;


import com.example.tgBot.entity.AuthorEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorListResponse extends BaseResponse{
    public AuthorListResponse(Iterable<AuthorEntity> data){
        super(true,"Авторы");
        this.data = data;
    }
    private Iterable<AuthorEntity> data;
}

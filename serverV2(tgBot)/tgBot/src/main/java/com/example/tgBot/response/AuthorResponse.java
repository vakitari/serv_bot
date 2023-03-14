package com.example.tgBot.response;


import com.example.tgBot.entity.AuthorEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorResponse extends BaseResponse {
    public AuthorResponse(Boolean success, String message, AuthorEntity author){
        super(success,message);
        this.author = author;
    }
    public  AuthorResponse(AuthorEntity author){super(true,"Author data");}
    private AuthorEntity author;
}

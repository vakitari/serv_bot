package com.example.tgBot.response;


import com.example.tgBot.entity.PublisherEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PublisherListResponse extends BaseResponse{
    public PublisherListResponse(Iterable<PublisherEntity> data){
        super(true,"Издательства");
        this.data = data;
    }
    private Iterable<PublisherEntity> data;
}

package com.example.evolveservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SendEmailDto {
    private Long emailListId;
    private String subject;

    private String body;
}

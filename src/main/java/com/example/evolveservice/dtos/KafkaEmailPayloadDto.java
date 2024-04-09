package com.example.evolveservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class KafkaEmailPayloadDto {
    private List<String> emailIds;
    private String subject;

    private String body;

    private String cc;


}

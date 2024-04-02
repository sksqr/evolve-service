package com.example.evolveservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EntitySearchDto {
    private String keyword;

    private List<EntityDto> entityDtoList;
}

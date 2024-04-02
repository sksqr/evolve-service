package com.example.evolveservice.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EntityDto {
    private String type;

    private String name;

    private Long id;
}

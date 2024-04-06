package com.example.evolveservice.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContactsInEmailListDto {
    private Long emailListId;
    List<Long> contactList;
}

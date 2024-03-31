package com.example.evolveservice.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdatePrimaryContactDto {


    @NotNull
    @Size(max = 255)
    private String clientId;


    @NotNull
    @Size(max = 255)
    private String primaryContactEmail;
}

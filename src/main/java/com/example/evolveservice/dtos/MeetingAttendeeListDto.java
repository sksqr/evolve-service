package com.example.evolveservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MeetingAttendeeListDto {

    private String keyword;

    private Long meetingId;

    private List<AttendeeNameIdDto> nameIdDtoList;
}

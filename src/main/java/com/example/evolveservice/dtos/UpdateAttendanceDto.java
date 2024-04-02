package com.example.evolveservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UpdateAttendanceDto {

    private Long meetingId;

    private List<Long> attendeeIdList;
}

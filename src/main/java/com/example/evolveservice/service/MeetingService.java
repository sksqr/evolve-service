package com.example.evolveservice.service;

import com.example.evolveservice.controller.AppController;
import com.example.evolveservice.dtos.*;
import com.example.evolveservice.entity.*;
import com.example.evolveservice.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingService {

    private static Logger LOGGER = LoggerFactory.getLogger(MeetingService.class);

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private MeetingRepo meetingRepo;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private AttendeeRepo attendeeRepo;

    public Long scheduleMeeting(MeetingDto meetingDto){
        LOGGER.info("Processing scheduleMeeting");
        List<Attendee> attendeeList = new ArrayList<>();
        Meeting meeting = new Meeting();
        for(AttendeeDto attendeeDto : meetingDto.getAttendeeDtos()){
            Attendee attendee = new Attendee();
            Account account = accountRepo.findByClientId(attendeeDto.getClientId());
            Contact contact = contactRepo.findById(attendeeDto.contactId).get();
            attendee.setAccount(account);
            attendee.setContact(contact);
            attendee.setName(contact.getName());
            attendee.setMeeting(meeting);
            attendeeList.add(attendee);
        }
        AppUser analyst = appUserRepo.findById(meetingDto.getAnalystId()).get();
        meeting.setAnalyst(analyst);
        meeting.setAttendees(attendeeList);
        meeting = meetingRepo.save(meeting);
        return meeting.getId();
    }

    public String updateAttendance(UpdateAttendanceDto updateAttendanceDto){
        // Select * from metting whrere id = updateAttendanceDto.getMeetingId()
        Meeting meeting = meetingRepo.findById(updateAttendanceDto.getMeetingId()).get();
        for(Attendee  attendee : meeting.getAttendees()){
            if(updateAttendanceDto.getAttendeeIdList().contains(attendee.getId())){
                attendee.setAttended(true);
                attendeeRepo.save(attendee);
            }
        }
        return "Done";
    }

    public MeetingAttendeeListDto getAttendeeList(String keyword, Long meetingId){
        MeetingAttendeeListDto meetingAttendeeListDto = new MeetingAttendeeListDto();
        meetingAttendeeListDto.setMeetingId(meetingId);
        meetingAttendeeListDto.setKeyword(keyword);
        Meeting meeting = meetingRepo.findById(meetingId).get();
        List<Attendee> attendeeList = attendeeRepo.findByNameStartingWithAndMeeting(keyword,meeting);
        // select * from attendee where meetingId= and name like '%%'
        List<AttendeeNameIdDto> attendeeNameIdDtos = new ArrayList<>();
        for (Attendee attendee : attendeeList){
            attendeeNameIdDtos.add(new AttendeeNameIdDto(attendee.getId(),attendee.getName()));
        }
        meetingAttendeeListDto.setNameIdDtoList(attendeeNameIdDtos);
        return meetingAttendeeListDto;
    }
}

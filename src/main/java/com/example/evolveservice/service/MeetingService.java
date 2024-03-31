package com.example.evolveservice.service;

import com.example.evolveservice.controller.AppController;
import com.example.evolveservice.dtos.MeetingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {

    private static Logger LOGGER = LoggerFactory.getLogger(MeetingService.class);


    public Long scheduleMeeting(MeetingDto meetingDto){
        LOGGER.info("Processing");
        return 0l;
    }
}

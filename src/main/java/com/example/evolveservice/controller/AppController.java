package com.example.evolveservice.controller;

import com.example.evolveservice.dtos.AccountDto;
import com.example.evolveservice.dtos.ContactDto;
import com.example.evolveservice.dtos.MeetingDto;
import com.example.evolveservice.dtos.UpdatePrimaryContactDto;
import com.example.evolveservice.entity.Contact;
import com.example.evolveservice.repo.ContactRepo;
import com.example.evolveservice.service.AccountService;
import com.example.evolveservice.service.ContactService;
import com.example.evolveservice.service.MeetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/evolve-service")
public class AppController {


    private static Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/createContact")
    public Long createContact(@RequestBody ContactDto contactDto){

        LOGGER.info("Processing createContact API : {}",contactDto);
        Long id = contactService.createContact(contactDto);
        return id;
    }


    @PostMapping("/createAccount")
    public Long createAccount(@RequestBody AccountDto accountDto){
        LOGGER.info("Processing createAccount API : {}",accountDto);
        Long id = accountService.createAccount(accountDto);
        return id;
    }

    @PostMapping("/mapPrimaryContact")
    public String mapPrimaryContact(@RequestBody UpdatePrimaryContactDto updatePrimaryContactDto){
        LOGGER.info("Processing mapPrimaryContact API : {}",updatePrimaryContactDto);
        String response = accountService.mapPrimaryContact(updatePrimaryContactDto);
        return response;
    }



    @PostMapping("/scheduleMeeting")
    public Long scheduleMeeting(@RequestBody MeetingDto meetingDto){
        LOGGER.info("Processing scheduleMeeting API : {}",meetingDto);
        Long meetingId = meetingService.scheduleMeeting(meetingDto);
        return meetingId;
    }
}
/*
spring bean: Object created in spring container.
 */

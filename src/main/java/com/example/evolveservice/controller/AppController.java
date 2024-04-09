package com.example.evolveservice.controller;

import com.example.evolveservice.dtos.*;
import com.example.evolveservice.entity.Contact;
import com.example.evolveservice.repo.ContactRepo;
import com.example.evolveservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
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

    @Autowired
    private EntityService entityService;

    @Autowired
    private EmailService emailService;

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

    @PutMapping("/updateAttendance")
    public String updateAttendance(@RequestBody UpdateAttendanceDto updateAttendanceDto){
        return meetingService.updateAttendance(updateAttendanceDto);
    }

    @GetMapping("/getAttendeeList")
    public MeetingAttendeeListDto getAttendeeList(@RequestParam String keyword, @RequestParam Long meetingId){
        return meetingService.getAttendeeList(keyword,meetingId);
    }

    @GetMapping("/searchEntities")
    public EntitySearchDto searchEntities(@RequestParam String keyword){
        return entityService.getEntities(keyword);
    }

    @PostMapping("/createEmailList")
    public Long createEmailList(@RequestParam String name){
        return emailService.createEmailList(name);
    }

    @PutMapping("/addContactsInEmailList")
    public String addContactsInEmailList(@RequestBody ContactsInEmailListDto contactsInEmailListDto){
        return emailService.addContactsInEmailList(contactsInEmailListDto);

    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody SendEmailDto sendEmailDto) throws ExecutionException, InterruptedException {
        String msg =  emailService.sendEmail(sendEmailDto);
        return ResponseEntity.accepted().body(msg);
    }

}
/*
spring bean: Object created in spring container.
 */

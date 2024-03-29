package com.example.evolveservice.controller;

import com.example.evolveservice.dtos.ContactDto;
import com.example.evolveservice.entity.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evolve-service")
public class AppController {

    private static Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @PostMapping("/createContact")
    public Long createContact(@RequestBody ContactDto contactDto){

        LOGGER.info("Processing createContact API : {}",contactDto);

        return 1l;

    }
}

package com.example.evolveservice.service;

import com.example.evolveservice.dtos.ContactsInEmailListDto;
import com.example.evolveservice.dtos.KafkaEmailPayloadDto;
import com.example.evolveservice.dtos.SendEmailDto;
import com.example.evolveservice.entity.Contact;
import com.example.evolveservice.entity.EmailList;
import com.example.evolveservice.repo.ContactRepo;
import com.example.evolveservice.repo.EmailListRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class EmailService {

    private static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);


    @Autowired
    private EmailListRepo emailListRepo;

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Long createEmailList(String name){
        EmailList emailList = new EmailList();
        emailList.setName(name);
        emailList = emailListRepo.save(emailList);
        return emailList.getId();
    }

    public String addContactsInEmailList(ContactsInEmailListDto contactsInEmailListDto){
        EmailList emailList = emailListRepo.findById(contactsInEmailListDto.getEmailListId()).get();
        List<Contact> contactList = new ArrayList<>();
        for(Long contactId : contactsInEmailListDto.getContactList()){
            Contact contact = contactRepo.findById(contactId).get();
            contactList.add(contact);
        }
        emailList.setContactList(contactList);
        emailListRepo.save(emailList);
        return "added";
    }

    public String sendEmail(SendEmailDto sendEmailDto) throws ExecutionException, InterruptedException {
        EmailList emailList = emailListRepo.findById(sendEmailDto.getEmailListId()).get();
        List<String> toEmails = new ArrayList<>();
        for(Contact contact: emailList.getContactList()){
            toEmails.add(contact.getEmail());
        }
        KafkaEmailPayloadDto kafkaEmailPayloadDto = new KafkaEmailPayloadDto();
        kafkaEmailPayloadDto.setEmailIds(toEmails);
        kafkaEmailPayloadDto.setSubject(sendEmailDto.getSubject());
        kafkaEmailPayloadDto.setBody(sendEmailDto.getBody());
        kafkaEmailPayloadDto.setCc("admin.blr01@yopmail.com");

        Future<SendResult<String,Object>> future =  kafkaTemplate.send("topic01",kafkaEmailPayloadDto.getEmailIds().get(0),kafkaEmailPayloadDto);
        LOGGER.info("Pushed payload to kafka: {}",future.get());

//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("jbdl.ewallet@gmail.com");
//        simpleMailMessage.setSubject(sendEmailDto.getSubject());
//        simpleMailMessage.setText(sendEmailDto.getBody());
//        simpleMailMessage.setCc("admin.blr01@yopmail.com");
//       // String[] toEmailArray = (String[]) toEmails.stream().toArray();
//        simpleMailMessage.setTo(toEmails.get(0));
//        javaMailSender.send(simpleMailMessage);
        return "Done";
    }
}

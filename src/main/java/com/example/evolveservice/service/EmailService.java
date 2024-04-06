package com.example.evolveservice.service;

import com.example.evolveservice.dtos.ContactsInEmailListDto;
import com.example.evolveservice.dtos.SendEmailDto;
import com.example.evolveservice.entity.Contact;
import com.example.evolveservice.entity.EmailList;
import com.example.evolveservice.repo.ContactRepo;
import com.example.evolveservice.repo.EmailListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailListRepo emailListRepo;

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private JavaMailSender javaMailSender;

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

    public String sendEmail(SendEmailDto sendEmailDto){
        EmailList emailList = emailListRepo.findById(sendEmailDto.getEmailListId()).get();
        List<String> toEmails = new ArrayList<>();
        for(Contact contact: emailList.getContactList()){
            toEmails.add(contact.getEmail());
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("jbdl.ewallet@gmail.com");
        simpleMailMessage.setSubject(sendEmailDto.getSubject());
        simpleMailMessage.setText(sendEmailDto.getBody());
        simpleMailMessage.setCc("");
        simpleMailMessage.setTo(toEmails.get(0));
        javaMailSender.send(simpleMailMessage);
        return "Done";
    }
}

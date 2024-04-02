package com.example.evolveservice.service;

import com.example.evolveservice.dtos.EntityDto;
import com.example.evolveservice.dtos.EntitySearchDto;
import com.example.evolveservice.entity.Account;
import com.example.evolveservice.entity.Contact;
import com.example.evolveservice.repo.AccountRepo;
import com.example.evolveservice.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ContactRepo contactRepo;

    public EntitySearchDto getEntities(String keyword){
        EntitySearchDto entitySearchDto = new EntitySearchDto();
        entitySearchDto.setKeyword(keyword);
        List<Account> accountList = accountRepo.findByClientNameStartingWith(keyword);
        List<EntityDto> entityDtoList = new ArrayList<>();
        for (Account account : accountList){
            EntityDto entityDto = new EntityDto("Account",account.getClientName(), account.getId());
            entityDtoList.add(entityDto);
        }
        List<Contact> contactList = contactRepo.findByNameStartingWith(keyword);
        for (Contact contact : contactList){
            EntityDto entityDto = new EntityDto("Contact",contact.getName(), contact.getId());
            entityDtoList.add(entityDto);
        }
        entitySearchDto.setEntityDtoList(entityDtoList);
        return entitySearchDto;
    }
}

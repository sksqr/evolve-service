package com.example.evolveservice.service;

import com.example.evolveservice.dtos.AccountDto;
import com.example.evolveservice.dtos.ContactDto;
import com.example.evolveservice.dtos.UpdatePrimaryContactDto;
import com.example.evolveservice.entity.Account;
import com.example.evolveservice.entity.Address;
import com.example.evolveservice.entity.Contact;
import com.example.evolveservice.enums.Status;
import com.example.evolveservice.repo.AccountRepo;
import com.example.evolveservice.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ContactRepo contactRepo;

    public Long createAccount(AccountDto accountDto){
        Account account = mapToEntity(accountDto);
        account = accountRepo.save(account);
        return account.getId();
    }

    public String mapPrimaryContact(UpdatePrimaryContactDto updatePrimaryContactDto){
        Account account = accountRepo.findByClientId(updatePrimaryContactDto.getClientId());
        Contact contact = contactRepo.findByEmail(updatePrimaryContactDto.getPrimaryContactEmail());
        account.setContact(contact);
        accountRepo.save(account);
        return "Done";
    }


    private Account mapToEntity(AccountDto accountDto){
        Account account = new Account();
        account.setClientName(accountDto.getName());
        account.setClientId(accountDto.getClientId());
        return account;
    }
}

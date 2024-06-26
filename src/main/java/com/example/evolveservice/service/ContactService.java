package com.example.evolveservice.service;

import com.example.evolveservice.dtos.AddressDto;
import com.example.evolveservice.dtos.ContactDto;
import com.example.evolveservice.entity.Address;
import com.example.evolveservice.entity.Contact;
import com.example.evolveservice.enums.Status;
import com.example.evolveservice.repo.AddressRepo;
import com.example.evolveservice.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private AddressRepo addressRepo;


    public Long createContact(ContactDto contactDto){
        Contact contact = mapToEntity(contactDto);
        // insert into contact values ()
        contact = contactRepo.save(contact);
        return contact.getId();
    }

    private Contact mapToEntity(ContactDto contactDto){
        Contact contact = new Contact();
        contact.setName(contactDto.getName());
        contact.setEmail(contactDto.getEmail());
        contact.setPhone(contactDto.getPhone());
        contact.setStatus(Status.ACTIVE);
        Address address = mapToEntity(contactDto.getAddressDto());
        address = addressRepo.save(address);
        contact.setAddress(address);
        return contact;
    }

    public Address mapToEntity(final AddressDto addressDTO) {
        final Address address = new Address();
        address.setLine1(addressDTO.getLine1());
        address.setLine2(addressDTO.getLine2());
        address.setPincode(addressDTO.getPincode());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        return address;
    }

}

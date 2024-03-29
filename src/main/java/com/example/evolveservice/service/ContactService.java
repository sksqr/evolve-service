package com.example.evolveservice.service;

import com.example.evolveservice.dtos.AddressDto;
import com.example.evolveservice.dtos.ContactDto;
import com.example.evolveservice.entity.Address;
import com.example.evolveservice.entity.Contact;
import com.example.evolveservice.enums.Status;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    public Long createContact(ContactDto contactDto){
        return 0l;
    }

    private Contact mapToEntity(ContactDto contactDto){
        Contact contact = new Contact();
        contact.setName(contactDto.getName());
        contact.setEmail(contactDto.getEmail());
        contact.setPhone(contactDto.getPhone());
        contact.setStatus(Status.ACTIVE);
        Address address = new Address();
        mapToEntity(contactDto.getAddressDto());
        //addressRepository.save(address);
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

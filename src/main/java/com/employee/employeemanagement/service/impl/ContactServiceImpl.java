package com.employee.employeemanagement.service.impl;

import com.employee.employeemanagement.dto.ContactDto;
import com.employee.employeemanagement.dto.ResponseDto;
import com.employee.employeemanagement.model.Contact;
import com.employee.employeemanagement.model.Employee;
import com.employee.employeemanagement.repo.ContactRepository;
import com.employee.employeemanagement.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author piyumi_navodani
 */
@Service
@Slf4j
public class ContactServiceImpl implements ContactService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private ContactRepository contactRepository;

    /**
     * this method is used to create a contact
     * @param contactDto
     * @return
     */
    @Override
    public ResponseDto<Contact> createContact(ContactDto contactDto) {
        log.info("ContactServiceImpl.createContact() method access...");
        ResponseDto responseDto = new ResponseDto<>();

        boolean uniqueContact = !contactRepository.existsByMobileNumber(contactDto.getMobileNumber());

        if(uniqueContact){
            Contact contact = new Contact();
            contact.setMobileNumber(contactDto.getMobileNumber());

            contactRepository.save(contact);

            ContactDto newContact = modelMapper.map(contact, ContactDto.class);

            responseDto.setMessage("Contact Created");
            log.info("Contact Created");
            responseDto.setStatus(HttpStatus.CREATED.value());
            responseDto.setData(newContact);
        }else {
            responseDto.setMessage("Contact Already Exist");
            log.info("Contact Already Exist");
            responseDto.setStatus(HttpStatus.CONFLICT.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    /**
     * this method is used to get all contact list
     * @return
     */
    @Override
    public ResponseDto<List<Contact>> getAllContacts() {
        log.info("ContactServiceImpl.getAllContacts() method access...");
        ResponseDto<List<Contact>> responseDto = new ResponseDto<>();
        List<Contact> contactList = contactRepository.findAll();
        if(!contactList.isEmpty()){

            List<ContactDto> contactDtoList = contactList.stream()
                    .map(contact -> modelMapper.map(contact, ContactDto.class))
                    .collect(Collectors.toList());

            responseDto.setMessage("Contact List Retrieved");
            log.info("Contact List Retrieved");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(contactDtoList);
        }else {
            responseDto.setMessage("Contact List is Empty");
            log.info("Contact List is Empty");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    /**
     * this method is used to get a contact by id
     * @param id
     * @return
     */
    @Override
    public ResponseDto<Contact> getContactById(Long id) {
        log.info("ContactServiceImpl.getContactById() method access...");
        ResponseDto responseDto = new ResponseDto();
        Optional<Contact> contact = contactRepository.findById(id);

        if(contact.isPresent()){

            ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

            responseDto.setMessage("Contact Retrieved");
            log.info("Contact Retrieved");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(contactDto);
        }else {
            responseDto.setMessage("Contact Not Found");
            log.info("Contact Not Found");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    /**
     * this method is used to update a contact
     * @param id
     * @param contact
     * @return
     */
    @Override
    public ResponseDto<Contact> updateContact(Long id, Contact contact) {
        log.info("ContactServiceImpl.getContactById() method access...");
        ResponseDto responseDto = new ResponseDto();
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if(contactOptional.isPresent()){
            Contact currentContact = contactOptional.get();
            currentContact.setMobileNumber(contact.getMobileNumber());

            contactRepository.save(currentContact);
            ContactDto updatedContact = modelMapper.map(currentContact, ContactDto.class);

            responseDto.setMessage("Contact Updated");
            log.info("Contact Updated");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(updatedContact);
        }else {
            responseDto.setMessage("Contact Not Found");
            log.info("Contact Not Found");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    /**
     * this method is used to delete a contact
     * @param id
     * @return
     */
    @Override
    public ResponseDto<Contact> deleteContact(Long id) {
        log.info("ContactServiceImpl.getContactById() method access...");
        ResponseDto responseDto = new ResponseDto();
        Optional<Contact> contact = contactRepository.findById(id);

        if(contact.isPresent()){

            Contact deletingContact = contact.get();
            contactRepository.delete(deletingContact);

            responseDto.setMessage("Contact deleted");
            log.info("Contact deleted");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(null);
        }else {
            responseDto.setMessage("Contact Not Found");
            log.info("Contact Not Found");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }

        return responseDto;
    }
}

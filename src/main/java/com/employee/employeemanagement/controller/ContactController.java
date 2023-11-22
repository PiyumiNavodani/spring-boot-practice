package com.employee.employeemanagement.controller;

import com.employee.employeemanagement.dto.ContactDto;
import com.employee.employeemanagement.dto.ResponseDto;
import com.employee.employeemanagement.model.Contact;
import com.employee.employeemanagement.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author piyumi_navodani
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/v1/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    /**
     * this method is used to create contact
     * @param contactDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ResponseDto<Contact>> createContact(@RequestBody ContactDto contactDto){
        log.info("ContactController.createContact() method access...");
        ResponseDto responseDto = contactService.createContact(contactDto);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to get all contact details
     * @return
     */
    @GetMapping
    public ResponseEntity<ResponseDto<List<Contact>>> getAllContact(){
        log.info("ContactController.getAllContact() method access...");
        ResponseDto<List<Contact>> responseDto = contactService.getAllContacts();
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to get contact details by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Contact>> getContactById(@PathVariable Long id){
        log.info("ContactController.getContactById() method access...");
        ResponseDto responseDto = contactService.getContactById(id);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to update a contact
     * @param contact
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Contact>> updateContact(@RequestBody Contact contact, @PathVariable Long id){
        log.info("ContactController.updateContact() method access...");
        ResponseDto responseDto = contactService.updateContact(id, contact);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to delete contact
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Contact>> deleteContact(@PathVariable Long id){
        log.info("ContactController.deleteContact() method access...");
        ResponseDto responseDto = contactService.deleteContact(id);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }
}

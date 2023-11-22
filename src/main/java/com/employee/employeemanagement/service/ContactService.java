package com.employee.employeemanagement.service;

import com.employee.employeemanagement.dto.ContactDto;
import com.employee.employeemanagement.dto.ResponseDto;
import com.employee.employeemanagement.model.Contact;
import com.employee.employeemanagement.model.Employee;

import java.util.List;

/**
 * @auther piyumi_navodani
 */
public interface ContactService {

    ResponseDto<Contact> createContact(ContactDto contactDto);

    ResponseDto<List<Contact>> getAllContacts();

    ResponseDto<Contact> getContactById(Long id);

    ResponseDto<Contact> updateContact(Long id, Contact contact);

    ResponseDto<Contact> deleteContact(Long id);
}

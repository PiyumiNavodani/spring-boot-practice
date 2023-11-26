package com.employee.employeemanagement.dto;

import com.employee.employeemanagement.model.Contact;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Set;

@Data
public class EmployeeDto {
    private long id;
    private String firstName;
    private String lastName;
    private String emailId;
    public Set<ContactDto> contactList;
}

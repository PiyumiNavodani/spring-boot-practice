package com.employee.employeemanagement.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EmployeeDto {
    private long id;
    private String firstName;
    private String lastName;
    private String emailId;
}

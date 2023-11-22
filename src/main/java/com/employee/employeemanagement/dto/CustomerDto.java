package com.employee.employeemanagement.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CustomerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}

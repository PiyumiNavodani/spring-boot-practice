package com.employee.employeemanagement.dto;

import jakarta.persistence.Column;
import lombok.Data;

/**
 * @author piyumi_navodani
 */
@Data
public class ContactDto {
    private long id;
    private int mobileNumber;
}

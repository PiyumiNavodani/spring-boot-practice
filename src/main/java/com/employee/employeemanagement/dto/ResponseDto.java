package com.employee.employeemanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto<T> {
    String message;
    int status;
    Object data;
}

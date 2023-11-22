package com.employee.employeemanagement.service;

import com.employee.employeemanagement.dto.CustomerDto;
import com.employee.employeemanagement.dto.ResponseDto;
import com.employee.employeemanagement.model.Customer;

import java.util.List;

public interface CustomerService {
    ResponseDto<Customer> createCustomer(CustomerDto customerDto);

    ResponseDto<List<Customer>> getAllCustomer();

    ResponseDto<Customer> getCustomerById(Long id);

    ResponseDto<Customer> updateCustomer(Customer customer, Long id);

    ResponseDto<Customer> deleteCustomer(Long id);
}

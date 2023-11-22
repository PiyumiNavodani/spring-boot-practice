package com.employee.employeemanagement.controller;

import com.employee.employeemanagement.dto.CustomerDto;
import com.employee.employeemanagement.dto.ResponseDto;
import com.employee.employeemanagement.model.Customer;
import com.employee.employeemanagement.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * this method is used to create a customer
     * @param customerDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ResponseDto<Customer>> saveCustomer(@RequestBody CustomerDto customerDto){
        log.info("CustomerController.saveCustomer() method access...");
        ResponseDto responseDto = customerService.createCustomer(customerDto);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to get all customer details
     * @return
     */
    @GetMapping
    public ResponseEntity<ResponseDto<List<Customer>>> getAllCustomer(){
        log.info("CustomerController.getAllCustomer() method access...");
        ResponseDto<List<Customer>> responseDto = customerService.getAllCustomer();
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to get customer details by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Customer>> getCustomerById(@PathVariable Long id){
        log.info("CustomerController.getCustomerById() method access...");
        ResponseDto<Customer> responseDto = customerService.getCustomerById(id);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to update customer details
     * @param customer
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Customer>> updateCustomer(@RequestBody Customer customer, @PathVariable Long id){
        log.info("CustomerController.updateCustomer() method access...");
        ResponseDto<Customer> responseDto = customerService.updateCustomer(customer, id);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to delete customer
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Customer>> deleteCustomer(@PathVariable Long id){
        log.info("CustomerController.deleteCustomer() method access...");
        ResponseDto<Customer> responseDto = customerService.deleteCustomer(id);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }
}

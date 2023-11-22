package com.employee.employeemanagement.service.impl;

import com.employee.employeemanagement.dto.CustomerDto;
import com.employee.employeemanagement.dto.ResponseDto;
import com.employee.employeemanagement.model.Customer;
import com.employee.employeemanagement.repo.CustomerRepository;
import com.employee.employeemanagement.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * this method is used to create a new customer
     * @param customerDto
     * @return
     */
    @Override
    public ResponseDto<Customer> createCustomer(CustomerDto customerDto) {
        log.info("CustomerServiceImpl.createCustomer() method access...");
        ResponseDto responseDto = new ResponseDto<>();

        boolean uniqueCustomer = !customerRepository.existsByFirstNameAndLastName(customerDto.getFirstName(), customerDto.getLastName());

        if(uniqueCustomer){
            Customer customer = new Customer();

            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setEmail(customerDto.getEmail());

            customerRepository.save(customer);

            CustomerDto newCustomer = modelMapper.map(customer, CustomerDto.class);

            responseDto.setMessage("Customer Created Successfully");
            log.info("Customer Created Successfully");
            responseDto.setStatus(HttpStatus.CREATED.value());
            responseDto.setData(newCustomer);
        }else {
            responseDto.setMessage("Customer Already Exists");
            log.info("Customer Already Exists");
            responseDto.setStatus(HttpStatus.CONFLICT.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    /**
     * this method is used to get the list of all customers
     * @return
     */
    @Override
    public ResponseDto<List<Customer>> getAllCustomer() {
        log.info("CustomerServiceImpl.getAllCustomer() method access...");
        ResponseDto<List<Customer>> responseDto = new ResponseDto<>();

        List<Customer> customerList = customerRepository.findAll();

        if(!customerList.isEmpty()){
            List<CustomerDto> customerDtoList = customerList.stream()
                    .map(customer -> modelMapper.map(customer, CustomerDto.class))
                    .collect(Collectors.toList());

            responseDto.setMessage("Customer List Retrieved");
            log.info("Customer List Retrieved");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(customerDtoList);
        }
        else {
            responseDto.setMessage("Customer List is Empty");
            log.info("Customer List is Empty");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    /**
     * this method is used to get a customer by id
     * @param id
     * @return
     */
    @Override
    public ResponseDto<Customer> getCustomerById(Long id) {
        log.info("CustomerServiceImpl.getCustomerById() method access...");
        ResponseDto responseDto = new ResponseDto<>();
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()){

            CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);

            responseDto.setMessage("Customer Retrieved");
            log.info("Customer Retrieved");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(customerDto);
        }
        else {
            responseDto.setMessage("Customer Not Found");
            log.info("Customer Not Found");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    /**
     * this. method is used to update customer
     * @param customer
     * @param id
     * @return
     */
    @Override
    public ResponseDto<Customer> updateCustomer(Customer customer, Long id) {
        log.info("CustomerServiceImpl.updateCustomer() method access...");
        ResponseDto responseDto = new ResponseDto<>();
        Optional<Customer> findCustomer = customerRepository.findById(id);

        if(findCustomer.isPresent()){

            Customer currentCustomer = findCustomer.get();
            currentCustomer.setFirstName(customer.getFirstName());
            currentCustomer.setLastName(customer.getLastName());
            currentCustomer.setEmail(customer.getEmail());

            customerRepository.save(currentCustomer);

            CustomerDto updatedCustomer = modelMapper.map(customer, CustomerDto.class);
            responseDto.setMessage("Customer Updated Succeddfully");
            log.info("Customer Updated Succeddfully");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(updatedCustomer);
        }else {
            responseDto.setMessage("Customer Not Found");
            log.info("Customer Not Found");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    /**
     * this method is used to delete customer
     * @param id
     * @return
     */
    @Override
    public ResponseDto<Customer> deleteCustomer(Long id) {
        log.info("CustomerServiceImpl.deleteCustomer() method access...");
        ResponseDto responseDto = new ResponseDto<>();
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){

            Customer deletingCustomer = customer.get();
            customerRepository.delete(deletingCustomer);

            responseDto.setMessage("Customer Deleted Successfully");
            log.info("Customer Deleted Successfully");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(null);
        }
        else {
            responseDto.setMessage("Customer Not Found");
            log.info("Customer Not Found");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }
        return responseDto;
    }
}

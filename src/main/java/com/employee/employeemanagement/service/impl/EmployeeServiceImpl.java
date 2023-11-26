package com.employee.employeemanagement.service.impl;

import com.employee.employeemanagement.dto.ContactDto;
import com.employee.employeemanagement.dto.EmployeeDto;
import com.employee.employeemanagement.dto.ResponseDto;
import com.employee.employeemanagement.model.Contact;
import com.employee.employeemanagement.model.Employee;
import com.employee.employeemanagement.repo.ContactRepository;
import com.employee.employeemanagement.repo.EmployeeRepository;
import com.employee.employeemanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author piyumi_navodani
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    ContactRepository contactRepository;

//    @Override
//    public Employee saveEmployee(Employee employee) {
//        return employeeRepository.save(employee);
//    }

    /**
     * this method is used to create employee
     * @param employeeDto
     * @return
     */
    @Override
    public ResponseDto<Employee> createEmployee(EmployeeDto employeeDto) {
        log.info("EmployeeServiceImpl.createEmployee() method access...");
        ResponseDto responseDto = new ResponseDto<>();

        for (ContactDto c : employeeDto.getContactList()){
            if (contactRepository.existsByMobileNumber(c.getMobileNumber())){
                responseDto.setMessage("Contact number "+ c.getMobileNumber()+" already exists");
                responseDto.setStatus(HttpStatus.CONFLICT.value());
                responseDto.setData(null);

                return responseDto;
            }
        }

        boolean uniqueEmployee = !employeeRepository.existsByFirstNameAndLastName(employeeDto.getFirstName(), employeeDto.getLastName());

        if(uniqueEmployee){
            Employee employee = new Employee();
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setEmailId(employeeDto.getEmailId());

            for (ContactDto c : employeeDto.getContactList()){
                Contact contact = new Contact();
                contact.setEmployee(employee);
                contact.setMobileNumber(c.getMobileNumber());
                contactRepository.save(contact);
            }

            employeeRepository.save(employee);

            EmployeeDto newEmployee = modelMapper.map(employee, EmployeeDto.class);

            responseDto.setMessage("Employee Created Successfully");
            log.info("Employee Created Successfully");
            responseDto.setStatus(HttpStatus.CREATED.value());
            responseDto.setData(newEmployee);
        }else {
            responseDto.setMessage("Employee Already Exists");
            log.info("Employee Already Exists");
            responseDto.setStatus(HttpStatus.CONFLICT.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    /**
     * this method is used to get all employee details
     * @return
     */
    @Override
    public ResponseDto<List<Employee>> getAllEmployees() {
        log.info("EmployeeService.getAllEmployees() method access...");
        ResponseDto responseDto = new ResponseDto<>();

//        get all employee and assign it to the employeeList
        List<Employee> employeeList = employeeRepository.findAll();

//        check whether the employeeList is empty or not
        if(!employeeList.isEmpty()){

//            convert employee list to employeeDto list
            List<EmployeeDto> employeeDtoList = employeeList.stream()
//                    for each to loop each employeeList to map with employeeDtoList
                            .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                                    .collect(Collectors.toList());
//            set message
            responseDto.setMessage("Employee List Retrieved");
            log.info("Employee List Retrieved");
//            set http status
            responseDto.setStatus(HttpStatus.CREATED.value());
//            set employeeDto
            responseDto.setData(employeeDtoList);
        }
        else{
            responseDto.setMessage("Employee List Empty");
            log.info("Employee List Empty");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }

//        Employee employee = new Employee();
//        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

//        return responseDto
        return responseDto;
    }

    /**
     * this method is used to get a employee by id
     * @param id
     * @return
     */
    @Override
    public ResponseDto<Employee> getEmployee(Long id) {
        log.info("EmployeeService.getEmployee() method access...");
        ResponseDto responseDto = new ResponseDto();
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
            responseDto.setMessage("Employee Retrieved");
            log.info("Employee Retrieved");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(employeeDto);
        }
        else {
            responseDto.setMessage("Employee Not Found");
            log.info("Employee Not Found");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    /**
     * this method is used to update employee
     * @param id
     * @param employee
     * @return
     */
    @Override
    public ResponseDto<Employee> updateEmployee(Long id, Employee employee) {
        log.info("EmployeeService.updateEmployee() method access...");
        ResponseDto responseDto = new ResponseDto();

        //validate id
        if(employeeRepository.findById(id).isEmpty()){
            responseDto.setMessage("Employee not found");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
//if not exists method eken eliyata yanawa meh return eken
            return responseDto;
        }
        if (employeeRepository.existsByFirstNameAndLastNameAndIdNot(employee.getFirstName(), employee.getLastName(),id)){
            responseDto.setMessage("Employee Already Exists");
            responseDto.setStatus(HttpStatus.CONFLICT.value());
            responseDto.setData(null);
            //there is an else condition so no need of a return
        }else {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);
            Employee currentEmployee = employeeOptional.get();
            currentEmployee.setFirstName(employee.getFirstName());
            currentEmployee.setLastName(employee.getLastName());
            currentEmployee.setEmailId(employee.getEmailId());

            employeeRepository.save(currentEmployee);

            responseDto.setMessage("Employee Updated");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(currentEmployee);
        }
        //the common return for both if and else
        return responseDto;
    }

    /**
     * this method is used to delete a employee
     * @param id
     * @return
     */
    @Override
    public ResponseDto<Employee> deleteEmployee(Long id) {
        log.info("EmployeeService.deleteEmployee() method access...");
        ResponseDto responseDto = new ResponseDto();
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            Employee deletingEmployee = employee.get();
            employeeRepository.delete(deletingEmployee);
            responseDto.setMessage("Employee deleted");
            log.info("Employee deleted");
            responseDto.setStatus(HttpStatus.OK.value());
            responseDto.setData(null);
        }
        else {
            responseDto.setMessage("Employee Not Found");
            log.info("Employee Not Found");
            responseDto.setStatus(HttpStatus.NOT_FOUND.value());
            responseDto.setData(null);
        }
        return responseDto;
    }

    @Override
    public ResponseDto<?> employeeCount() {
        ResponseDto responseDto = new ResponseDto<>();

        responseDto.setMessage("Employee Count Retrieved");
        responseDto.setStatus(HttpStatus.OK.value());
        responseDto.setData(employeeRepository.count());

        return responseDto;
    }


}

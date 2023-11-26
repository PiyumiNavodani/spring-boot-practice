package com.employee.employeemanagement.controller;

import com.employee.employeemanagement.dto.EmployeeDto;
import com.employee.employeemanagement.dto.ResponseDto;
import com.employee.employeemanagement.exception.ResourceNotFoundException;
import com.employee.employeemanagement.model.Employee;
import com.employee.employeemanagement.repo.EmployeeRepository;
import com.employee.employeemanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther piyumi_navodani
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/employees")
@Slf4j
public class EmployeeController {

//create object from EmployeeService interface
    @Autowired
    private EmployeeService employeeService;

    /**
     * this method is used to get all employee details
     * @return
     */
    @GetMapping()
    public ResponseEntity<ResponseDto<List<Employee>>> getAllEmployees(){
        log.info("EmployeeController.getAllEmployees() method access...");
        ResponseDto<List<Employee>> responseDto = employeeService.getAllEmployees();
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     *this method is used to create a employee
     * @param employeeDto
     * @return
     */
    @PostMapping()
    public ResponseEntity<ResponseDto<Employee>> createEmployee(@RequestBody EmployeeDto employeeDto){
        log.info("EmployeeController.createEmployee() method access...");
        ResponseDto responseDto = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to get a employee details by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Employee>> getEmployeeById(@PathVariable Long id){
        log.info("EmployeeController.getEmployeeById() method access...");
        ResponseDto responseDto = employeeService.getEmployee(id);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to update employee by id
     * @param id
     * @param employeeDetails
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Employee>> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        log.info("EmployeeController.updateEmployee() method access...");
        ResponseDto responseDto = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    /**
     * this method is used to delete employee by id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Employee>> deleteEmployee(@PathVariable Long id){
        log.info("EmployeeController.deleteEmployee() method access...");
        ResponseDto responseDto= employeeService.deleteEmployee(id);
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }

    @GetMapping("/count")
    public ResponseEntity<?> employeeCount(){
        ResponseDto responseDto = employeeService.employeeCount();
        return ResponseEntity.status(responseDto.getStatus()).body(responseDto);
    }
}

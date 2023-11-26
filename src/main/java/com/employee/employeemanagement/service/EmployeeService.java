package com.employee.employeemanagement.service;

import com.employee.employeemanagement.dto.EmployeeDto;
import com.employee.employeemanagement.dto.ResponseDto;
import com.employee.employeemanagement.model.Employee;

import java.util.List;

public interface EmployeeService {
//    Employee saveEmployee(Employee employee);

    ResponseDto<Employee> createEmployee(EmployeeDto employeeDto);

    ResponseDto<List<Employee>> getAllEmployees();

    ResponseDto<Employee> getEmployee(Long id);

    ResponseDto<Employee> updateEmployee(Long id, Employee employeepg);

    ResponseDto<Employee> deleteEmployee(Long id);

    ResponseDto<?>employeeCount();
}

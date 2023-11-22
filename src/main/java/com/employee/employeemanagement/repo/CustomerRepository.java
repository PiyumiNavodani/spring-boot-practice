package com.employee.employeemanagement.repo;

import com.employee.employeemanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}

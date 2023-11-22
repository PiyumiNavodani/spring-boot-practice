package com.employee.employeemanagement.repo;

import com.employee.employeemanagement.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther piyumi_navodani
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    boolean existsByMobileNumber(int mobileNumber);
}

package com.employee.employeemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * @auther piyumi_navodani
 */
@Entity
@Table(name = "employees")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    Set<Contact> contactList;

}

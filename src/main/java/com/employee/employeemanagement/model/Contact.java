package com.employee.employeemanagement.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @auther piyumi_navodani
 */
@Entity
@Table(name = "contact")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mobile_number")
    private int mobileNumber;
}

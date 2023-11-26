package com.employee.employeemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author piyumi_navodani
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("contact")
    private Employee employee;
}

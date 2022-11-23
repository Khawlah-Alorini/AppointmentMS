package com.example.demo.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Patient {
    @Id
    @Column(name = "PK_ID")
    private Long id;

    @Column(name = "PATIENT_ID_NUMBER")
    private Long patientIdNumber;

    @Column(name = "PATIENT_NAME")
    private String patientName;

    @Column(name = "DATE_OF_BIRTH")
    private Data dateOfBirth;
}

package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ID")
    private Long id;

    @Column(name = "APPOINTMENT_NUMBER")
    private Long appointmentNumber;

    @Column(name = "APPOINTMENT_DATE")
    private LocalDateTime appointmentDate;

    @ManyToOne
    @JoinColumn(name = "FK_PATIENT_ID")
    private Patient patient;

    @Column(name = "APPOINTMENT_STATUS")
    private AppointmentStatus appointmentStatus;

    @Column(name = "CANCELLATION_REASON")
    private CancellationReason cancellationReason;

    @Column(name = "OTHER_CANCELLATION_REASON")
    private String otherCancellationReason;

}

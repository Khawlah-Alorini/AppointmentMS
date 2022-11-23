package com.example.demo.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDto {

    private Long appointmentId;
    private Long patientId;
    private LocalDateTime appointmentDate;
    private String cancellationReason;
    private String otherCancellationReason;

}

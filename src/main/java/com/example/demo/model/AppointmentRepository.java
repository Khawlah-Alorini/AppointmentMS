package com.example.demo.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByAppointmentDateBetween(LocalDateTime dataStart, LocalDateTime dataEnd);

    Optional<Appointment> findById(Long id);

    List<Appointment> findByPatient_PatientName(String patientName);

}

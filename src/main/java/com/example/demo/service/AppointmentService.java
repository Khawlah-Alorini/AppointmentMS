package com.example.demo.service;


import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AppointmentService {



    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientService patientService;

    public List<Appointment> listAllAppointment(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> listAllAppointmentBySpecificDate(SearchCreteria searchCreteria){
        if(searchCreteria.getAppointmentDate() != null) {
            return appointmentRepository.findAllByAppointmentDateBetween(
                    theDayStartAt(LocalDate.parse(searchCreteria.getAppointmentDate())),
                    theDayEndAt(LocalDate.parse(searchCreteria.getAppointmentDate())));
        } else
            return appointmentRepository.findAllByAppointmentDateBetween(
                    theDayStartAt(LocalDate.now()),
                    theDayEndAt(LocalDate.now()));

    }


    private LocalDateTime theDayStartAt(LocalDate localDate){
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }
    private LocalDateTime theDayEndAt(LocalDate localDate){
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }


    public Appointment createNewAppointment(AppointmentDto appointmentDto){
        // check the admin role or throw auth sec
        Appointment appointment = new Appointment();
        Patient patient = patientService.findOnePatientByIdNumber(appointmentDto.getPatientId());
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setPatient(patient);
        // we should create sequences to avoid repeate and be more clearfy
        appointment.setAppointmentNumber(new Random().nextLong());
        appointment.setAppointmentStatus(AppointmentStatus.RESERVED);
        return appointmentRepository.save(appointment);
    }

    public Appointment cancelAppointment(AppointmentDto appointmentDto) throws Exception {
        // check the admin role
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentDto.getAppointmentId());
        if(!appointment.isPresent()){
            throw new Exception("not_found");
        } else {
            appointment.get().setAppointmentStatus(AppointmentStatus.CANCELLED);
            appointment.get().setCancellationReason(CancellationReason.valueOf(appointmentDto.getCancellationReason()));
            return appointmentRepository.save(appointment.get());
        }
    }

    private void mapCancellationReason(Appointment appointment, String cancellationReason, String OtherReason) {
        appointment.setCancellationReason(CancellationReason.valueOf(cancellationReason));
        if(CancellationReason.valueOf(cancellationReason) == CancellationReason.OTHER_REASON){
            appointment.setOtherCancellationReason(OtherReason);
        }
    }

    public List<Appointment> getListOfAppointmentsRelatedToPatientName(SearchCreteria searchCreteria) {
        return appointmentRepository.findByPatient_PatientName(searchCreteria.getPatientName());
    }

}

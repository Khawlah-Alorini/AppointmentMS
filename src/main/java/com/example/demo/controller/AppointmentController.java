package com.example.demo.controller;


import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentDto;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.SearchCreteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("get-list-of-appointment")
    public ResponseEntity<List<Appointment>> listAllAppointment(@RequestBody SearchCreteria searchCreteria){
        return (ResponseEntity<List<Appointment>>) appointmentService.listAllAppointmentBySpecificDate(searchCreteria);
    }

    @PostMapping("create-new-appointment")
    public Appointment addNewAppointment(@RequestBody AppointmentDto appointmentDto){
        return appointmentService.createNewAppointment(appointmentDto);
    }

    @PutMapping("cancel-appointment")
    public Appointment cancelAppointment(@RequestBody AppointmentDto appointmentDto) throws Exception {
        return appointmentService.cancelAppointment(appointmentDto);
    }

    @GetMapping("list-of-patient-appointment")
    public List<Appointment> listOfPatientAppointment(@RequestBody SearchCreteria searchCreteria) throws Exception {
        return appointmentService.getListOfAppointmentsRelatedToPatientName(searchCreteria);
    }
}

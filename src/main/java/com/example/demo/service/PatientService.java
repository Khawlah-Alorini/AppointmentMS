package com.example.demo.service;


import com.example.demo.model.Patient;
import com.example.demo.model.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient findOnePatientByIdNumber(Long patientId){
        // if not found handle exception
        return patientRepository.findByPatientIdNumber(patientId);
    }

}

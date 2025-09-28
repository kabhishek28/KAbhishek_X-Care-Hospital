package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DoctorServiceImp implements DoctorService{

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public String deleteDoctorData(int doctorID) {
        return doctorRepository.deleteDoctorData(doctorID);
    }
}

package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.PatientsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface PatientsService {
    String savePatientsDetails(PatientsDTO patientsDTO);
}

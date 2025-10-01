package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.dto.PatientsDTO;
import com.xworkz.kabhishek_xcare_hospital.entity.PatientsEntity;
import org.springframework.stereotype.Repository;

public interface PatientsRepository {
    String savePatientsDetails(PatientsEntity patientsEntity);
}

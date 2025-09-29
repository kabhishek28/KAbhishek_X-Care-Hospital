package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;

import java.io.IOException;
import java.util.List;

public interface DoctorService {

    void saveDoctor(DoctorDTO dto) throws IOException;

    List<DoctorDTO> getAllDoctorsList();

    DoctorDTO findSingleDoctorData(int doctorID);

    String saveUpdatedDoctorData(DoctorDTO doctorDTO) throws IOException;

    String deleteDoctorData(int doctorID);
}

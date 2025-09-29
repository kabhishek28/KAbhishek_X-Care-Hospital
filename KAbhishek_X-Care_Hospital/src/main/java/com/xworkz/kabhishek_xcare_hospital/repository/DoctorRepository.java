package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.ImageEntity;

import java.util.List;

public interface DoctorRepository {

    int saveDoctor(DoctorEntity doctorEntity);

    DoctorEntity findSingleDoctorData(int doctorID);

    String saveDoctorImageDetails(ImageEntity imageEntity);

    List<DoctorEntity> getAllDoctorsList();

    String saveUpdatedDoctorData(DoctorEntity doctorEntity);

    String saveUpdatedDoctorImageDetails(ImageEntity imageEntity);

    String deleteDoctorData(int doctorID);
}

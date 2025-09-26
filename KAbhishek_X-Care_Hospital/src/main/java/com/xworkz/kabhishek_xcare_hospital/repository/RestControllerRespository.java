package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.TimingSlotEntity;

import java.util.List;

public interface RestControllerRespository {
    int countEmail(String email);

    List<DoctorEntity> checkDoctorList(String specialty);

    List<TimingSlotEntity> checkTimingList(String specialty);

    int checkDoctorSlotsAssign(String doctorEmail, String slotTime);

}

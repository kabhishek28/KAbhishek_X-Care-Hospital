package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;

import java.util.List;

public interface RestControllerSercive {
    int countEmail(String email);

    List<DoctorDTO> checkDoctorList(String specialty);

    List<TimingSlotDTO> checkTimingList(String specialty);

    int checkDoctorSlotsAssign(String doctorEmail,String slotTime);
}

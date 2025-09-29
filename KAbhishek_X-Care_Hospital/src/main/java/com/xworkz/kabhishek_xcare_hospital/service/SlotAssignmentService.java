package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.DoctorSlotAssignmentDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;

import java.util.List;

public interface SlotAssignmentService {

    List<DoctorDTO> findDoctorList(String specialty);

    List<TimingSlotDTO> findTimingList(String specialty);

    String saveDoctorWithSlots(DoctorSlotAssignmentDTO doctorWithSlots);
}

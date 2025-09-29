package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorSlotAssignmentEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.TimingSlotEntity;

import java.util.List;

public interface SlotAssignmentRepository {

    List<DoctorEntity> findDoctorList(String specialty);

    List<TimingSlotEntity> findTimingList(String specialty);

    DoctorEntity getDoctorEntityByID(int doctorID);

    TimingSlotEntity getTimingSlotEntityByID(int slotID);


    String saveDoctorWithSlots(DoctorSlotAssignmentEntity doctorWithSlotsEntity);


}

package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.*;

import javax.print.Doc;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

public interface HospitalRepository {
    AdminEntity checkAdminExist(String gmail);


    void saveOTP(String OTP , LocalDateTime localDateTime, HttpSession session);

    AdminEntity getAdminEntity(String gmail);

    int saveDoctor(DoctorEntity doctorEntity);

    void saveTimingSlots(TimingSlotEntity timingSlotEntity);

    List<DoctorEntity> findDoctorList(String specialty);

    List<TimingSlotEntity> findTimingList(String specialty);

    String upDateDoctorAndSlots(String doctorEmail,String specialty,String timings,String startTime,String endTime);

     List<DoctorEntity> getAllDoctorsList();

    DoctorEntity findSingleDoctorData(int doctorID);

    String saveUpdatedDoctorData(DoctorEntity doctorEntity);



    DoctorEntity getDoctorEntityByID(int doctorID);

    TimingSlotEntity getTimingSlotEntityByID(int slotID);


    String saveDoctorWithSlots(DoctorSlotAssignmentEntity doctorWithSlotsEntity);

    String saveDoctorImageDetails(ImageEntity imageEntity);

    String saveUpdatedDoctorImageDetails(ImageEntity imageEntity);

}

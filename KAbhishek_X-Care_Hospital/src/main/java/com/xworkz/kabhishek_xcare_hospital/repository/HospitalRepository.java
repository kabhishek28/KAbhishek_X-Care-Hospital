package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorWithSlotsDTO;
import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorWithSlotsEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.TimingSlotEntity;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

public interface HospitalRepository {
    AdminEntity checkAdmin(String gmail);

    int countEmail(String email);

    void saveOTP(String OTP , LocalDateTime localDateTime, HttpSession session);

    AdminEntity getAdminEntity(String gmail);

    void saveDoctor(DoctorEntity doctorEntity);

    void saveTimingSlots(TimingSlotEntity timingSlotEntity);

    List<DoctorEntity> findDoctorList(String specialty);

    List<TimingSlotEntity> findTimingList(String specialty);

    String upDateDoctorAndSlots(String doctorEmail,String specialty,String timings,String startTime,String endTime);

     int checkDoctorSlotsAssign(String doctorEmail, String slotTime);

    List<DoctorEntity> checkDoctorList(String specialty);

    List<TimingSlotEntity> checkTimingList(String specialty);

    String saveDoctorWithSlots(DoctorWithSlotsEntity doctorWithSlotsEntity);
}

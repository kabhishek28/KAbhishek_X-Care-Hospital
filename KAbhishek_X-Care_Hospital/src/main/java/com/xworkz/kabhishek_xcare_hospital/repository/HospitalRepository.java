package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.entity.TimingSlotEntity;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public interface HospitalRepository {
    AdminEntity checkAdmin(String gmail);

    int countEmail(String email);

    void saveOTP(String OTP , LocalDateTime localDateTime, HttpSession session);

    AdminEntity getAdminEntity(String gmail);

    void saveDoctor(DoctorEntity doctorEntity);

    void saveTimingSlots(TimingSlotEntity timingSlotEntity);
}

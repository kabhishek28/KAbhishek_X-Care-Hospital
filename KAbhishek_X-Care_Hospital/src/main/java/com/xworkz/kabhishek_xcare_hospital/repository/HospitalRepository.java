package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.*;


import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


public interface HospitalRepository {

    AdminEntity checkAdminExist(String gmail);

    void saveOTP(String OTP , LocalDateTime localDateTime, HttpSession session);

    AdminEntity getAdminEntity(String gmail);

   // String upDateDoctorAndSlots(String doctorEmail,String specialty,String timings,String startTime,String endTime);

}

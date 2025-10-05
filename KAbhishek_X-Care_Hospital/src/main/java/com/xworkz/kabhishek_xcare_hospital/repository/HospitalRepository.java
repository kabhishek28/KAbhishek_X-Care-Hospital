package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.*;


import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;


public interface HospitalRepository {

    AdminEntity checkAdminExist(String gmail);

    void saveOTP(String OTP , LocalDateTime localDateTime, HttpSession session);

    AdminEntity getAdminEntity(String gmail);

    List<AdminEntity> getAllAdmin();

    void upDateAdminEntity(AdminEntity adminEntity);

   // String upDateDoctorAndSlots(String doctorEmail,String specialty,String timings,String startTime,String endTime);

}

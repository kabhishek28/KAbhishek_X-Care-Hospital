package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;

public interface HospitalService {
//    String checkAdmin(String gmail, HttpSession session);
    Map<String,Object> checkAdmin(String gmail,HttpSession session);

    int countEmail(String email);

    LocalDateTime saveOTP(String otp, LocalDateTime localDateTime ,HttpSession session);

    String matchOtp(String gmail,String inputOTP);

    void saveDoctor(DoctorDTO dto);

    void saveTimeSlots(TimingSlotDTO timingSlotDTO);


}

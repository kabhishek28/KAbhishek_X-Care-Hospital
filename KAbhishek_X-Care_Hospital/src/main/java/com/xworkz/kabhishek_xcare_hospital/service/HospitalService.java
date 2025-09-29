package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.DoctorSlotAssignmentDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface HospitalService {

    Map<String,Object> checkAdminExist(String gmail,HttpSession session);

    LocalDateTime saveOTP(String otp, LocalDateTime localDateTime ,HttpSession session);

    String matchOtp(String gmail,String inputOTP);

//    String upDateDoctorAndSlots(String doctorEmail,String specialty,String timings,String startTime,String endTime);

}

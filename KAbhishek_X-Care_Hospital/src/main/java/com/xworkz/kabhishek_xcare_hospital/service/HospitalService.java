package com.xworkz.kabhishek_xcare_hospital.service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public interface HospitalService {
    String checkAdmin(String gmail, HttpSession session);

    int countEmail(String email);

    void saveOTP(String otp, LocalDateTime localDateTime ,HttpSession session);

    boolean matchOtp(String gmail,String inputOTP);
}

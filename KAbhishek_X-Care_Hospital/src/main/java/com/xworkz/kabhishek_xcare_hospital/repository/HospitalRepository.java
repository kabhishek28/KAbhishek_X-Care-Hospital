package com.xworkz.kabhishek_xcare_hospital.repository;

import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public interface HospitalRepository {
    AdminEntity checkAdmin(String gmail);

    int countEmail(String email);

    void saveOTP(String OTP , LocalDateTime localDateTime, HttpSession session);
}

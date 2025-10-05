package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.entity.*;
import com.xworkz.kabhishek_xcare_hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Service
public  class HospitalServiceImp implements HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public Map<String,Object> checkAdminExist(String gmail, HttpSession session){
        AdminEntity adminEntity = hospitalRepository.checkAdminExist(gmail);
        Map<String,Object> response = new HashMap<>();
        if(adminEntity.getEmail() == null){
            response.put("otpSent" , false);
            response.put("message","Gmail Not exits");
            return response;
        }
        String  genOTP  = sendEmail(gmail,generateOTP());
        LocalDateTime localDateTime = saveOTP(genOTP,LocalDateTime.now(),session);
        session.setAttribute("otpGeneratedTime", localDateTime);
        session.setAttribute("otpExpirySeconds", 120);

        response.put("otpSent", true);
        response.put("remainingTime", 120);
        return response;
    }

    public String generateOTP(){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0;i<6;i++){
            int digit = secureRandom.nextInt(10);
            stringBuilder.append(digit);
        }
        return stringBuilder.toString();
    }

    private String sendEmail(String email,String OTPNumber){
        final String username = "kabhishek.eng@gmail.com";
        final String password = "voyy beef kyoc ahsn";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Your One-Time Password (OTP) for Verification");

            message.setText("Dear User,\n\n"
                    + "Your One-Time Password (OTP) for completing the verification process is:\n\n"
                    + OTPNumber + "\n\n"
                    + "Please use this code within the next 10 minutes. "
                    + "Do not share this OTP with anyone for security reasons.\n\n"
                    + "Thank you,\n"
                    + "Support Team");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return OTPNumber;
    }




    @Override
    public LocalDateTime saveOTP(String otp,LocalDateTime localDateTime,HttpSession session) {
        hospitalRepository.saveOTP(otp,localDateTime,session);
        return  localDateTime;
    }

    @Override
    public String matchOtp(String gmail, String inputOTP) {
        AdminEntity adminEntity = hospitalRepository.getAdminEntity(gmail);
        if(adminEntity.getOtp() == null && adminEntity.getLocalDateTime() == null){
            return "resend OTP";
        }else {
            LocalDateTime generatedTime = adminEntity.getLocalDateTime();
            LocalDateTime expiryTime = generatedTime.plusSeconds(150);
            if(!adminEntity.getOtp().equals(inputOTP)){
                return "OTP Wrong";
            } else if(LocalDateTime.now().isAfter(expiryTime)){
                return "Time expired";
            }else {
                return "OTP Done";
            }
        }
    }

//    @Override
//    public String upDateDoctorAndSlots(String doctorEmail, String specialty,String timings, String startTime, String endTime) {
//        return hospitalRepository.upDateDoctorAndSlots(doctorEmail,specialty,timings,startTime,endTime);
//    }

}

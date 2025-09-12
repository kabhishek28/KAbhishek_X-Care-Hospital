package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.entity.AdminEntity;
import com.xworkz.kabhishek_xcare_hospital.repository.HospitalRepository;
import com.xworkz.kabhishek_xcare_hospital.repository.HospitalRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Properties;

@Service
public  class HospitalServiceImp implements HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public String checkAdmin(String gmail,HttpSession session) {
        AdminEntity adminEntity = hospitalRepository.checkAdmin(gmail);
        if(adminEntity.getEmail() == null){
            return "gmail not exist";
        }
        System.out.println("==================================="+session.getAttribute("email"));
        String genOTP = sendEmail(adminEntity.getEmail(),generateOTP());
        LocalDateTime localDateTime = LocalDateTime.now();
        saveOTP(genOTP,localDateTime,session);
        return "gmail exist";
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
    public int countEmail(String email) {
        return hospitalRepository.countEmail(email);
    }

    @Override
    public void saveOTP(String otp,LocalDateTime localDateTime,HttpSession session) {
        hospitalRepository.saveOTP(otp,localDateTime,session);
    }
}

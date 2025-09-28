package com.xworkz.kabhishek_xcare_hospital.service;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.DoctorSlotAssignmentDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;
import com.xworkz.kabhishek_xcare_hospital.entity.*;
import com.xworkz.kabhishek_xcare_hospital.repository.HospitalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.print.Doc;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        LocalDateTime generatedTime = adminEntity.getLocalDateTime();
        LocalDateTime expiryTime = generatedTime.plusSeconds(150);
        if(!adminEntity.getOtp().equals(inputOTP)){
            return "OTP Wrong";
        }
        if(LocalDateTime.now().isAfter(expiryTime)){
            return "Time expired";
        }
        return "OTP Done";
    }

    @Override
    public void saveDoctor(DoctorDTO dto) throws IOException {
        DoctorEntity doctorEntity = new DoctorEntity();
        ImageEntity imageEntity = new ImageEntity();

        MultipartFile file = dto.getPhoto();
        byte[] photoSize=file.getBytes();
        Path imagePath= Paths.get("D:\\doctorfolder\\"+dto.getDoctorName()+System.currentTimeMillis()+".jpg");
        Files.write(imagePath,photoSize);

        BeanUtils.copyProperties(dto,doctorEntity);
        int doctorId = hospitalRepository.saveDoctor(doctorEntity);

        imageEntity.setOriginalImageName(file.getOriginalFilename());
        imageEntity.setChangedName(imagePath.getFileName().toString());
        imageEntity.setImageSize(file.getSize());
        imageEntity.setImagePath(imagePath.toString());
        DoctorEntity doctor = hospitalRepository.findSingleDoctorData(doctorId);
        imageEntity.setDoctorEntity(doctor);
        hospitalRepository.saveDoctorImageDetails(imageEntity);


    }

    @Override
    public void saveTimeSlots(TimingSlotDTO timingSlotDTO) {
        TimingSlotEntity timingSlotEntity = new TimingSlotEntity();
        BeanUtils.copyProperties(timingSlotDTO,timingSlotEntity);
        hospitalRepository.saveTimingSlots(timingSlotEntity);
    }

    @Override
    public List<DoctorDTO> findDoctorList(String specialty) {

        List<DoctorEntity> list =  hospitalRepository.findDoctorList(specialty);
        List<DoctorDTO> doctors = new ArrayList<>();

        for (DoctorEntity doctorEntity : list){
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(doctorEntity,doctorDTO);
            doctors.add(doctorDTO);
        }

        return doctors;
    }

    @Override
    public List<TimingSlotDTO> findTimingList(String specialty) {
        List<TimingSlotEntity> list = hospitalRepository.findTimingList(specialty);
        List<TimingSlotDTO> listDTO = new ArrayList<>();
        for(TimingSlotEntity timingSlotEntity:list){
            TimingSlotDTO timingSlotDTO = new TimingSlotDTO();
            BeanUtils.copyProperties(timingSlotEntity,timingSlotDTO);
            listDTO.add(timingSlotDTO);
        }
        return listDTO;
    }

    @Override
    public String upDateDoctorAndSlots(String doctorEmail, String specialty,String timings, String startTime, String endTime) {
        return hospitalRepository.upDateDoctorAndSlots(doctorEmail,specialty,timings,startTime,endTime);
    }

    @Override
    public List<DoctorDTO> getAllDoctorsList() {
        List<DoctorEntity> doctorsList = hospitalRepository.getAllDoctorsList();
        List<DoctorDTO> doctorsDTOList = new ArrayList<>();

        for(DoctorEntity doctor:doctorsList){

            ImageEntity imageEntity = doctor.getImageEntity();
            if(imageEntity == null){
                DoctorDTO doctorDTO = new DoctorDTO();
                BeanUtils.copyProperties(doctor,doctorDTO);
                doctorsDTOList.add(doctorDTO);
            }else {
                DoctorDTO doctorDTO = new DoctorDTO();
                String imagePath = doctor.getImageEntity().getChangedName();
                BeanUtils.copyProperties(doctor,doctorDTO);
                doctorDTO.setImagePath(imagePath);
                doctorsDTOList.add(doctorDTO);
            }

        }
        return doctorsDTOList;
    }

    @Override
    public DoctorDTO findSingleDoctorData(int doctorID) {
        DoctorEntity doctorEntity = hospitalRepository.findSingleDoctorData(doctorID);
        ImageEntity imageEntity = doctorEntity.getImageEntity();
        DoctorDTO doctorDTO = new DoctorDTO();
        if(imageEntity == null){
            BeanUtils.copyProperties(doctorEntity,doctorDTO);
        }else {
            BeanUtils.copyProperties(doctorEntity,doctorDTO);
            doctorDTO.setImagePath(imageEntity.getChangedName());
        }

        return doctorDTO;
    }

    @Override
    public String saveUpdatedDoctorData(DoctorDTO doctorDTO) throws IOException {
        DoctorEntity doctorEntity = new DoctorEntity();
        ImageEntity imageEntity = new ImageEntity();

        MultipartFile file = doctorDTO.getPhoto();
        byte[] photoSize = file.getBytes();
        Path imagePath = Paths.get("D:\\doctorfolder\\"+doctorDTO.getDoctorName()+System.currentTimeMillis()+".jpg");
        Files.write(imagePath,photoSize);

        BeanUtils.copyProperties(doctorDTO,doctorEntity);
        String value = hospitalRepository.saveUpdatedDoctorData(doctorEntity);

        DoctorEntity doctor = hospitalRepository.findSingleDoctorData(doctorDTO.getId());

        imageEntity.setId(doctor.getImageEntity().getId());
        imageEntity.setOriginalImageName(file.getOriginalFilename());
        imageEntity.setChangedName(imagePath.getFileName().toString());
        imageEntity.setImageSize(file.getSize());
        imageEntity.setImagePath(imagePath.toString());
        imageEntity.setDoctorEntity(doctor);
        String value1 =  hospitalRepository.saveUpdatedDoctorImageDetails(imageEntity);

        if (!value.equals("data saved") && !value1.equals("Updated Doctor Data Saved")) {
            return "Data not Saved";
        }else {
            return  "Data Saved";
        }


    }




    @Override
    public String saveDoctorWithSlots(DoctorSlotAssignmentDTO doctorWithSlots) {
        DoctorEntity doctorEntity = hospitalRepository.getDoctorEntityByID(doctorWithSlots.getDoctorID());
        TimingSlotEntity timingSlotEntity = hospitalRepository.getTimingSlotEntityByID(doctorWithSlots.getSlotID());

        DoctorSlotAssignmentEntity doctorSlotAssignmentEntity = new DoctorSlotAssignmentEntity();
        doctorSlotAssignmentEntity.setId(doctorWithSlots.getId());
        doctorSlotAssignmentEntity.setOptionDoctorName(doctorWithSlots.getOptionDoctorName());
        doctorSlotAssignmentEntity.setDoctorEmail(doctorWithSlots.getDoctorEmail());
        doctorSlotAssignmentEntity.setSpecialty(doctorWithSlots.getSpecialty());
        doctorSlotAssignmentEntity.setTimings(doctorWithSlots.getTimings());
        doctorSlotAssignmentEntity.setDoctorEntity(doctorEntity);
        doctorSlotAssignmentEntity.setTimingSlotEntity(timingSlotEntity);

        return hospitalRepository.saveDoctorWithSlots(doctorSlotAssignmentEntity);
    }
}

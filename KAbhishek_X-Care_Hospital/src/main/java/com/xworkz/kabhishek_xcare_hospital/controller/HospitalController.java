package com.xworkz.kabhishek_xcare_hospital.controller;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.DoctorWithSlotsDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;
import com.xworkz.kabhishek_xcare_hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
@Slf4j
public class HospitalController {


    @Autowired
    HospitalService hospitalService;

    @RequestMapping("adminLogin")
    public String adminPage(){
        return "adminLogin";
    }

    @RequestMapping("getHome")
    public String getHomePage(){
        return "home";
    }
//        @RequestMapping("getotp")
//        public ModelAndView adminLogIn(String gmailName , ModelAndView model){
//            String value = hospitalService.checkAdmin(gmailName);
//            model.addObject("message",value);
//            model.setViewName("admin");
//            return model;
//        }

//    @RequestMapping("sendotp")
//    public String adminLogIn(String gmailName , Model model , HttpSession session){
//
//        session.setAttribute("email" , gmailName);
//        String value = hospitalService.checkAdmin(gmailName ,session);
//
//
//       if(value.equals("gmail exist")){
//           model.addAttribute("gmail",session.getAttribute("email"));
//
//           return "otp";
//       }else {
//           session.invalidate();
//           model.addAttribute("message",value);
//           return "admin";
//       }
//    }


    @RequestMapping("sendAdminOTP")
    public String adminLogIn(String gmailName,Model model,HttpSession session){
        session.setAttribute("email",gmailName);
        Map<String,Object> response = hospitalService.checkAdminExist(gmailName,session);

        if(!(boolean) response.get("otpSent")){
            model.addAttribute("gmail",session.getAttribute("email"));
           return "adminLogin";
        }
        model.addAttribute("gmail", gmailName);
        model.addAttribute("remainingTime", response.get("remainingTime"));
        return "adminLoginOTP";
    }

    @RequestMapping("login")
    public ModelAndView loginPage(@RequestParam String gmailName, @RequestParam String otp, HttpSession session, ModelAndView modelAndView) {

        // Check OTP
        String value = hospitalService.matchOtp(gmailName, otp);

        switch (value) {
            case "OTP Done":
                // OTP matched → redirect to home page
                modelAndView.setViewName("home");
                return modelAndView;

            case "OTP Wrong":
                // OTP wrong → stay on OTP page, timer continues
                Integer remainingTime = (Integer) session.getAttribute("remainingTime");
                if (remainingTime == null) remainingTime = 120; // fallback

                modelAndView.addObject("otpError", "OTP NOT MATCH");
                modelAndView.addObject("gmail", gmailName);
                modelAndView.addObject("remainingTime", remainingTime);
                modelAndView.setViewName("adminLoginOTP");
                return modelAndView;

            case "Time expired":
                // OTP expired → show resend button
                modelAndView.addObject("gmail", gmailName);
                modelAndView.addObject("timeExpired", true);
                modelAndView.setViewName("adminLoginOTP");
                return modelAndView;

            default:
                // fallback
                modelAndView.addObject("gmail", gmailName);
                modelAndView.setViewName("adminLoginOTP");
                return modelAndView;
        }
    }


    @RequestMapping("registerDoctor")
    public String getDoctorForm(){
        return "doctorRegisterForm";
    }

    @RequestMapping("doctorForm")
    public String saveDoctorFrom(DoctorDTO dto , @RequestParam("photo") MultipartFile file,Model model) throws IOException {
        byte[] photoSize=file.getBytes();
        Path imagePath=Paths.get("D:\\doctorfolder\\"+dto.getDoctorName()+System.currentTimeMillis()+".jpg");
        Files.write(imagePath,photoSize);
        dto.setImagePath(imagePath.getFileName().toString());


        model.addAttribute("dto",dto);
        hospitalService.saveDoctor(dto);
        return "doctor";
    }

    @GetMapping("download")
    public void previewImage(HttpServletResponse response,@RequestParam String imagePath) throws IOException {
        response.setContentType("image/jpeg");
        File file=new File("D:\\doctorfolder\\"+imagePath);
        InputStream inputStream=new BufferedInputStream(new FileInputStream(file));
        ServletOutputStream servletOutputStream=response.getOutputStream();
        IOUtils.copy(inputStream,servletOutputStream);
        response.flushBuffer();
    }

    @RequestMapping("setSlot")
    public String defineSlotTiming(){
        return "slotTiming";
    }

    @RequestMapping("saveSlotTiming")
    public String saveSlotTiming(TimingSlotDTO timingSlot ){

        LocalTime start = LocalTime.parse(timingSlot.getStartTime(),DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime end = LocalTime.parse(timingSlot.getEndTime(),DateTimeFormatter.ofPattern("HH:mm"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedStart = start.format(formatter);
        String formattedEnd = end.format(formatter);

        timingSlot.setStartTime(formattedStart);
        timingSlot.setEndTime(formattedEnd);

        hospitalService.saveTimeSlots(timingSlot);
        return "slotTiming";
    }

    @RequestMapping("assignSlot")
    public String assignSlotPage(){
        return "assignslot";
    }

    @RequestMapping("findDoctor")
    public String getDoctor(String specialty,Model model) {
        List<DoctorDTO> doctors = hospitalService.findDoctorList(specialty);
        List<TimingSlotDTO> timingSlot = hospitalService.findTimingList(specialty);

        if (doctors.size()==0) {
            model.addAttribute("message", "Doctor not exists");
        } else {
            model.addAttribute("doctors", doctors);
            model.addAttribute("slots",timingSlot);
            model.addAttribute("specialtyy",specialty);
            model.addAttribute("selectedSpecialty", specialty);
        }
        return "assignslot";
    }

//    @RequestMapping("/doctorSlotAssign")
//    public String assignDoctorSlot(@RequestParam(required=false) String doctorName,@RequestParam String specialty, Model model) {
//        List<DoctorDTO> doctors = hospitalService.findDoctorList(specialty);
//        model.addAttribute("doctors", doctors);
//
//        if (doctorName != null) {
//            model.addAttribute("selectedDoctor", doctorName);
//
//            // find email for selected doctor
//            String email = doctors.stream()
//                    .filter(d -> d.getDoctorName().equals(doctorName))
//                    .map(DoctorDTO::getDoctorEmail)
//                    .findFirst()
//                    .orElse("");
//            model.addAttribute("doctorEmail", email);
//        }
//
//        return "assignslot"; // JSP page
//    }


    @RequestMapping("doctorSlotAssign")
    public String assignSlotWithDoctor(DoctorWithSlotsDTO doctorWithSlots, Model model){
        System.out.println(doctorWithSlots);
        String value = hospitalService.saveDoctorWithSlots(doctorWithSlots);



//        String[] times = timings.split("\\|");
//        String startTime = times[0];
//        String endTime = times[1];
//
//        String value = hospitalService.upDateDoctorAndSlots(doctorEmail,specialty, timings,startTime,endTime);
//        System.out.println(value);
//        if(!value.equals("Doctor updated: 1, Slot updated: 1")){
//            model.addAttribute("messageAssign","Slot not Saved");
//            return "assignslot";
//        }else {
//            model.addAttribute("messageAssignSaved","slot saved");
//
//        }
        if(!value.equals("Data has been Saved")){
            model.addAttribute("saveMessageError","Slot could not be assigned to the doctor.");
            return "assignslot";
        }else {
            model.addAttribute("saveMessage","Slot has been successfully assigned to the doctor.");
            return "assignslot";
        }

    }

    @RequestMapping("getPatients")
    public String getPatientsPage(){
        return "patients";
    }

    @RequestMapping("getUpDatePage")
    public String getUpDate(Model model){
        List<DoctorDTO> list = hospitalService.getAllDoctorsList();
        model.addAttribute("doctorsList",list);
        return "doctorsData";
    }

    @RequestMapping("getDoctorUpdatePage")
    public String getUpdatePage(String email,Model model){
        DoctorDTO doctorDTO = hospitalService.findSingleDoctorData(email);
        System.out.println(doctorDTO);
        model.addAttribute("dto",doctorDTO);
        return "doctorUpDatePage";
    }

    @RequestMapping("updateDoctorForm")
    public String updateDoctordata(DoctorDTO doctorDTO,@RequestParam("photo") MultipartFile file,Model model) throws IOException {
        byte[] photoSize = file.getBytes();
        Path imagePath=Paths.get("D:\\doctorfolder\\"+doctorDTO.getDoctorName()+System.currentTimeMillis()+".jpg");
        Files.write(imagePath,photoSize);
        doctorDTO.setImagePath(imagePath.getFileName().toString());
        System.out.println(doctorDTO);
        String value = hospitalService.saveUpdatedDoctorData(doctorDTO);
        if(!value.equals("Data Saved")){
            model.addAttribute("updatedDataNotSaved","Updated Doctor Data Not Saved");
            return "doctorUpDatePage";
        }else {
            model.addAttribute("updatedDataSaved","Updated Doctor Data Saved");
            return "doctorUpDatePage";
        }
    }
}
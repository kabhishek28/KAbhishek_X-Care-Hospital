package com.xworkz.kabhishek_xcare_hospital.controller;

import com.xworkz.kabhishek_xcare_hospital.constants.Specialty;
import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.dto.TimingSlotDTO;
import com.xworkz.kabhishek_xcare_hospital.entity.DoctorEntity;
import com.xworkz.kabhishek_xcare_hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

@Controller
@RequestMapping("/")
@Slf4j
public class HospitalController {


    @Autowired
    HospitalService hospitalService;

    @RequestMapping("admin")
    public String adminPage(){
        return "admin";
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


    @RequestMapping("sendotp")
    public String adminLogIn(String gmailName,Model model,HttpSession session){
        session.setAttribute("email",gmailName);
        Map<String,Object> response = hospitalService.checkAdmin(gmailName,session);

        if(!(boolean) response.get("otpSent")){
            model.addAttribute("error",response.get("message"));
            model.addAttribute("gmail",session.getAttribute("email"));
           return "admin";
        }
        model.addAttribute("gmail", gmailName);
        model.addAttribute("remainingTime", response.get("remainingTime"));
        return "otp"; // JSP page where timer will be shown
    }

    @RequestMapping("login")
    public ModelAndView loginPage(String gmailName , String otp ,ModelAndView modelAndView ){
        String value = hospitalService.matchOtp(gmailName,otp);
        if(value.equals("OTP Done")){
            modelAndView.addObject("otpError","OTP MATCH");
            modelAndView.setViewName("home");
            return modelAndView;
        }else if(value.equals("OTP Wrong")) {
            modelAndView.addObject("otpError", "OTP NOT MATCH");
            modelAndView.addObject("gmail",gmailName);
            modelAndView.addObject("remainingTime", 150);
            modelAndView.setViewName("otp");
            return modelAndView;
        }else if(value.equals("Time expired")){
            modelAndView.addObject("gmail",gmailName);
            modelAndView.addObject("time","Time Expired");
            modelAndView.setViewName("otp");
            return modelAndView;
        }
        modelAndView.addObject("gmail",gmailName);
        modelAndView.setViewName("otp");
        return modelAndView;
    }

    @RequestMapping("getDoctor")
    public String getDoctorForm(){
        return "doctor";
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

    @RequestMapping("assingSlot")
    public String assingSlotPage(){
        return "assingslot";
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
        return "assingslot";
    }

//    @RequestMapping("/doctorSlotAssing")
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
//        return "assingslot"; // JSP page
//    }


    @RequestMapping("doctorSlotAssing")
    public String assingSlotWithDoctor( String optionDoctorName,String timings,String doctorEmail,String specialty){
        String[] times = timings.split("\\|");
        String startTime = times[0];
        String endTime = times[1];

        String value = hospitalService.upDateDoctorAndSlots(doctorEmail,specialty,startTime,endTime);

        return "";
    }
}
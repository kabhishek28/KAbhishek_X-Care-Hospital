package com.xworkz.kabhishek_xcare_hospital.controller;

import com.xworkz.kabhishek_xcare_hospital.dto.DoctorDTO;
import com.xworkz.kabhishek_xcare_hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


        String value = hospitalService.matchOtp(gmailName, otp);

        switch (value) {
            case "OTP Done":

                modelAndView.setViewName("home");
                return modelAndView;

            case "OTP Wrong":
                Integer remainingTime = (Integer) session.getAttribute("remainingTime");
                if (remainingTime == null) remainingTime = 120; // fallback

                modelAndView.addObject("otpError", "OTP NOT MATCH");
                modelAndView.addObject("gmail", gmailName);
                modelAndView.addObject("remainingTime", remainingTime);
                modelAndView.setViewName("adminLoginOTP");
                return modelAndView;

            case "Time expired":
                modelAndView.addObject("gmail", gmailName);
                modelAndView.addObject("timeExpired", true);
                modelAndView.setViewName("adminLoginOTP");
                return modelAndView;

            default:
                modelAndView.addObject("gmail", gmailName);
                modelAndView.setViewName("adminLoginOTP");
                return modelAndView;
        }
    }











    @RequestMapping("getPatients")
    public String getPatientsPage(){
        return "patients";
    }



}
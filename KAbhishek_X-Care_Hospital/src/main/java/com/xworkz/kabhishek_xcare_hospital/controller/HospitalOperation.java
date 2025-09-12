package com.xworkz.kabhishek_xcare_hospital.controller;

import com.xworkz.kabhishek_xcare_hospital.service.HospitalService;
import com.xworkz.kabhishek_xcare_hospital.service.HospitalServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@Slf4j
public class HospitalOperation {


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

    @RequestMapping("sendotp")
    public String adminLogIn(String gmailName , Model model , HttpSession session){

        session.setAttribute("email" , gmailName);
        String value = hospitalService.checkAdmin(gmailName ,session);


       if(value.equals("gmail exist")){
           model.addAttribute("gmail",session.getAttribute("email"));
           return "otp";
       }else {
           session.invalidate();
           model.addAttribute("message",value);
           return "admin";
       }
    }
}
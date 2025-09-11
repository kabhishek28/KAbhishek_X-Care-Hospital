package com.xworkz.kabhishek_xcare_hospital.controller;

import com.xworkz.kabhishek_xcare_hospital.service.HospitalService;
import com.xworkz.kabhishek_xcare_hospital.service.HospitalServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

        @RequestMapping("getotp")
        public ModelAndView adminLogIn(String gmailName , ModelAndView model){
            String value = hospitalService.checkAdmin(gmailName);
            model.addObject("message" , value);
            model.setViewName("admin");
            return model;
        }

}

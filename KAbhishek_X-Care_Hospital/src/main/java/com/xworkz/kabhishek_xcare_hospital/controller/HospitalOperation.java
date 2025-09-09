package com.xworkz.kabhishek_xcare_hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HospitalOperation {

    @RequestMapping("admin")
    public String adminPage(){
        return "admin";
    }


}
